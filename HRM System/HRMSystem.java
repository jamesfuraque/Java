import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HRMSystem extends Application {
    private List<Employee> employeeList;
    private ListView<Employee> employeeListView;
    private Button processPayrollButton;
    private VBox employeeForm;
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField hourlyRateField;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("HRM and Payroll System");

        // Load existing employees from file or create a new list
        employeeList = loadEmployees("employees.dat");
        if (employeeList == null) {
            employeeList = new ArrayList<>();
        }

        // Create employee form
        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField hourlyRateField = new TextField();

        Button addButton = new Button("Add Employee");
        addButton.setOnAction(e -> addEmployee());

        employeeForm = new VBox(10);
        employeeForm.getChildren().addAll(
                new Label("First Name:"), firstNameField,
                new Label("Last Name:"), lastNameField,
                new Label("Hourly Rate:"), hourlyRateField,
                addButton
        );

        // Create employee list view
        employeeListView = new ListView<>();
        employeeListView.getItems().addAll(employeeList);

        // Create payroll processing button
        Button processPayrollButton = new Button("Process Payroll");
        processPayrollButton.setOnAction(e -> processPayroll());
        processPayrollButton = new Button("Process Payroll");
        processPayrollButton.setOnAction(e -> processPayroll());

        // Create user authentication components
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> authenticateUser(usernameField.getText(), passwordField.getText()));

        VBox loginBox = new VBox(10);
        loginBox.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton);

        // Set up the main layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(employeeForm, employeeListView, processPayrollButton, loginBox);

        // Initially, disable features requiring authentication
        processPayrollButton.setDisable(true);
        employeeForm.setDisable(true);

        primaryStage.setScene(new Scene(layout, 400, 400));
        primaryStage.setOnCloseRequest(e -> saveEmployees("employees.dat"));
        primaryStage.show();
    }

    private void addEmployee() {
        Employee employee = new Employee();
        employee.setFirstName(firstNameField.getText());
        employee.setLastName(lastNameField.getText());
        employee.setHourlyRate(Double.parseDouble(hourlyRateField.getText()));
        employeeList.add(employee);

        // Update the employee list view
        employeeListView.getItems().setAll(employeeList);

        // Clear the form fields
        firstNameField.clear();
        lastNameField.clear();
        hourlyRateField.clear();
    }

    private void processPayroll() {
        // Detailed payroll processing logic
        // For simplicity, let's just calculate total payroll cost as an example
        double totalPayroll = employeeList.stream().mapToDouble(Employee::calculatePayroll).sum();

        // Display a detailed payroll report
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Detailed Payroll Report");
        alert.setHeaderText(null);
        alert.setContentText("Total Payroll Cost: $" + totalPayroll);
        alert.showAndWait();
    }

    private void authenticateUser(String username, String password) {
        // For simplicity, assume there is a single admin user with hardcoded credentials
        if (username.equals("admin") && password.equals("password")) {
            // Enable features requiring authentication
            processPayrollButton.setDisable(false);
            employeeForm.setDisable(false);
        } else {
            // Display authentication failure message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Authentication Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password");
            alert.showAndWait();
        }
    }

    private List<Employee> loadEmployees(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<Employee>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File not found is expected for the first run
            return null;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void saveEmployees(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(employeeList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

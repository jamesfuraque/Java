import java.util.Scanner;

/**
 * This is a simple program that gets student's details and grades.
 * After that, we're going to save their grades
 * and calculate it's average.
 *
 * @author James Furaque /  Jam Furaque
 * @version 1.0
 **/

public class Main {

    // CREATE A HELPER METHOD TO
    // VALIDATE THE GRADES THAT WILL BE PROVIDED
    // BY THE USER.
    private static double getValidGrade(Scanner scan, String subject) {
        double grade;
        while (true) {
            System.out.println(subject + ": ");
            grade = scan.nextDouble();
            if (grade >= 1 && grade <= 100) {
                // BREAK IF THE GRADE IS VALID
                break;
            } else {
                System.out.println("Please enter a valid grade between 1 and 100.");
            }
        }
        return grade;
    }

    // METHOD TO IDENTIFY IF
    // THE STUDENT FAILED OR PASSED.
    public static String statusResult(double gpa){
        String statusResult;
        if (gpa < 50 ) {
            statusResult = "Failed";
        } else {
            statusResult = "Passed";
        }
        return statusResult;
    }

    // THIS IS THE MAIN PROGRAM
    public static void main(String[] args) {

        System.out.println("\nWELCOME STUDENT!");
        System.out.println("Please provide your information below.\n");

    do{
        // ASK FOR THE STUDENT'S DETAILS
            Scanner scan = new Scanner(System.in);
            String answer;
            System.out.println("First Name: ");
            String firstName = scan.nextLine();
            System.out.println("Last Name: ");
            String lastName = scan.nextLine();
            System.out.println("Student ID:");
            int studentID = scan.nextInt();
            System.out.println("Program Year: (1, 2, 3, 4)");
            byte programYear = scan.nextByte();

        // ASK FOR THE STUDENT'S GRADES IN THE GIVEN SUBJECTS
            System.out.println("\n\nPlease enter your grades on different subjects.");
            double mathematics = getValidGrade(scan, "Math");
            double science = getValidGrade(scan, "Science");
            double english = getValidGrade(scan, "English");

        // CALCULATE THE AVERAGE OF THEIR GRADE
        // DON'T FOR GET TO USE MATH.ROUND
        // TO ROUND THE GRADE IN 2 DECIMAL PLACES
            double gpa = (mathematics + science + english) / 3;
            String fullName = firstName + " " + lastName;


        // PRINT THE RESULTS FOR THE STUDENT
            // SHOW THEIR DETAILS
            System.out.println("\n\nYour details are shown below.\n");
            System.out.println("Student's Name: " + fullName);
            System.out.println("Student ID: " + studentID);
            System.out.println("Program Year: " + programYear);

            // SHOW THEIR AVERAGE / GPA
            System.out.printf("GPA: " + "%.2f",gpa);

            // SHOW THE STATUS IF THEY FAILED OR PASS
            String status = statusResult(gpa);
            System.out.println("\nStatus: " + status);

            // ASK THE USER
            // IF THEY WANT TO RE-RUN THE PROGRAM OR NOT
            while (true) {
                System.out.print("\n\nDo you want to restart the program? (Y/N): ");
                answer = scan.next();

                if (answer.equalsIgnoreCase("Y")) {
                    // CONTINUE WITH OUR LOOP
                    scan.nextLine();
                    break;
                } else if (answer.equalsIgnoreCase("N")) {
                    // EXIT THE PROGRAM
                    System.out.println("Exiting the program.");
                    return;
                } else {
                    // MAKE SURE THAT THE USER
                    // PROVIDES VALID OPTION
                    System.out.println("Please provide a valid option.");
                }
            }

        } // CONTINUE THE PROGRAM
          // IF THE USER WANTS TO CONTINUE
            while (true);
    }
}

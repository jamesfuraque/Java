import java.util.InputMismatchException;
import java.util.Scanner;
/**
* This simple program gets the
* Name, Age, Address and 5 hobbies of the user
* and printing it back to them
 *
 * @author James Furaque / Jam Furaque
 * @version 1.1
 * In this version, I also implemented validations for
 * Age & Street Number
**/

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

    // USER'S NAME (FIRST NAME & LAST NAME).
        System.out.println("Welcome to my simple program!");
        System.out.println("Enter your first name: ");
        String firstName = scan.nextLine();
        System.out.println("Enter your last Name: ");
        String lastName = scan.nextLine();
        String fullName = (firstName + " " +  lastName);

    // USER'S AGE.
        byte age = -1;
        while (age < 0) {
            System.out.println("Please, enter your age:");
            try {
                age = scan.nextByte();
                if (age < 0) {
                    System.out.println("Age must be more than 0. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                scan.next();
            }
        }
        scan.nextLine();

    // USER'S ADDRESS.
        System.out.println("\n\nThis is for your Address.\nPlease provide the specific details below.");
        System.out.println("(Street Number/Street, City, Province, Postal Code)\n ");

        // STREET NUMBER WITH VALIDATION
        int streetNumber = -1;
        while (streetNumber < 0){
            System.out.println("Street Number: ");
            try {
                streetNumber = scan.nextInt();
                if (streetNumber < 0){
                    System.out.println("Please enter a valid street number.");
                }
            } catch (InputMismatchException e){
                System.out.println("Please enter a valid street number.");
                scan.next();
            }
        }
        scan.nextLine();

        System.out.println("Street Name:");
        String streetName = scan.nextLine();

        System.out.println("City: ");
        String city = scan.nextLine();

        System.out.println("Province: ");
        String province = scan.nextLine();

        System.out.println("Postal Code: ");
        String postal_code = scan.nextLine();

    //THIS IS FOR THE USER'S HOBBIES
        System.out.println("\n\nLet us know your 5 favourite hobbies!");
        System.out.println("Hobby 1: ");
        String hobby1 = scan.nextLine();
        System.out.println("Hobby 2: ");
        String hobby2 = scan.nextLine();
        System.out.println("Hobby 3: ");
        String hobby3 = scan.nextLine();
        System.out.println("Hobby 4: ");
        String hobby4 = scan.nextLine();
        System.out.println("Hobby 5");
        String hobby5 = scan.nextLine();


        System.out.println("\n\nGreat!\nYour details are here!\n");
        System.out.println("Full Name: " + fullName);
        System.out.println("Age: " + age + " years old.");
        System.out.println("Address:  " + streetNumber + " " + streetName + " " + city + " " + province + " " + postal_code);
        System.out.println("Top 5 Hobbies: " + hobby1 + ", " + hobby2 + ", " + hobby3 + ", " + hobby4 + " and " + hobby5 +  ".");



    }
    @Override
    public String toString() {
        return "Main []";
    }
}

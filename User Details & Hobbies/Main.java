import java.util.Scanner;
/**
* This simple program gets the
* Name, Age, Address and 5 hobbies of the user
* and printing it back to them
 *
 * @author James Furaque / Jam Furaque
 * @version 1.0
**/

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

    //THIS IS FOR THE USER'S NAME.
        System.out.println("Welcome to my simple program!");
        System.out.println("Enter your first name: ");
        String firstName = scan.nextLine();
        System.out.println("Enter your last Name: ");
        String lastName = scan.nextLine();
        String fullName = (firstName + " " +  lastName);

    //THIS IS FOR THE USER'S AGE.
        System.out.println("\nPlease, enter your Age:");
        byte age = scan.nextByte();
        scan.nextLine();

    //THIS IS FOR THE USER'S ADDRESS.
        System.out.println("\n\nThis is for your Address.\nPlease provide the specific details below.");
        System.out.println("(Street Number/Street, City, Province, Postal Code)\n ");
        System.out.println("Street Number: ");
        String streetNumber = scan.nextLine();

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
}

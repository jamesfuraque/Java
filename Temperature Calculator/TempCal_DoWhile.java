import java.io.IOException;
import java.lang.String;
import java.util.Scanner;

/**
 * This is my simple program that
 * converts Fahrenheit to Celsius and Celsius to Fahrenheit.
 *
 * @author James Furaque /  Jam Furaque
 * @version 1.0
 **/

public class TempCal_DoWhile {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String answer;
        do {
            // ASK THE USER TO CHOOSE THE MODE OF CONVERSION THEY WANT
            System.out.println("\nPlease choose the mode of conversion.");
            System.out.println("(1.) Fahrenheit to Celsius.");
            System.out.println("(2.) Celsius to Fahrenheit.");
            int result = scan.nextInt();

            // CALCULATING FAHRENHEIT INTO CELSIUS
            if (result == 1) {
                System.out.println("FAHRENHEIT to CELSIUS");
                System.out.println("\nEnter the temperature in fahrenheit: ");
                double fahr_temp = scan.nextDouble();
                double fahrToCel = (fahr_temp - 32) * 5 / 9;

                // PRINT THE RESULT
                // MAKE SURE TO ROUND INTO 2 DECIMAL PLACES
                System.out.println(String.format("%.2f Fahrenheit in Celsius is approximately: %.2f Celsius.", fahr_temp, fahrToCel));


            // CALCULATING CELSIUS TO FAHRENHEIT
            } else if (result == 2) {
                System.out.println("CELSIUS to FAHRENHEIT");
                System.out.println("\nEnter the temperature in celsius: ");
                double cel_temp = scan.nextDouble();
                double celToFahr = (cel_temp * 9 / 5 + 32);

                // FORMULA FOR CELSIUS TO FAHRENHEIT CAN ALSO BE (Celsius * 1.8 + 32)
                // PRINT THE RESULT
                // MAKE SURE TO ROUND INTO 2 DECIMAL PLACES
                System.out.println(String.format("%.2f Celsius in Fahrenheit is approximately: %.2f Fahrenheit.", cel_temp, celToFahr));


            // VALIDATION
            // MAKE SURE THE USER ONLY CHOOSE BETWEEN 1 AND 2
            } else {
                System.out.println("Please, choose between 1 and 2 only.");
            }

            // AFTER THE CONVERSION
            // ASK THE USER IF THEY WANT TO CONTINUE OR EXIT THE PROGRAM.
            while (true) {
                System.out.print("\nDo you still want to convert? (Y/N): ");
                answer = scan.next();
                if (answer.equalsIgnoreCase("Y")) {
                    // CONTINUE THE LOOP IF THEY WANT TO CONVERT MORE.
                    break;
                } else if (answer.equalsIgnoreCase("N")) {
                    // EXIT THE PROGRAM IF THEY DON'T WANT TO CONTINUE
                    System.out.println("Exiting the program.");
                    return;
                } else {
                    // MAKE SURE THE USER ONLY ENTERS Y OR N
                    // SMALL Y/N ARE ALSO CONSIDERED AS AN ANSWER.
                    System.out.println("Please provide a valid option.");
                }
            }
        } while (true);
    }
}


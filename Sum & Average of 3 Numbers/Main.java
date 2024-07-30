import java.util.Scanner;

/**
 * This is a simple program that;
 * Adds 3 numbers, getting the sum
 * and average of those numbers.
 *
 * @author James Furaque / Jam Furaque
 * @version 1.1
 */

// USING INT & STRINGS
// IN THIS PRACTICE PROGRAM,
// WE WILL ASK THE USER TO GIVE 3 NUMBERS AND GET THE SUM AND AVERAGE OF IT.
public class Main {
    public static void main(String[] args)
    {
        do {
            System.out.println("\nGive 3 numbers and get the sum and average of those numbers.\n");

            // AS FOR THE NUMBERS
            Scanner scan = new Scanner(System.in);
            String answer;
            System.out.println("Enter the first number: ");
            int num1 = scan.nextInt();
            System.out.println("Enter the second number: ");
            int num2 = scan.nextInt();
            System.out.println("Enter the third number: ");
            int num3 = scan.nextInt();

            // MAKE THE "SUM" VARIABLE
            // STORE THE SUM OF 3 NUMBERS TO THAT VARIABLE.
            int sum = num1 + num2 + num3;

            // MAKE THE "AVERAGE" VARIABLE
            // STORE THE AVERAGE OF THE GIVEN 3 NUMBERS
            // BY DIVIDING THE SUM INTO 3.
            double average = sum / 3;

            // PRINT THE RESULTS
            System.out.println("\n\nResults:");
            System.out.println("Sum = " + sum);
            System.out.println("Average = " + average);

            // ASK THE USER IF THEY STILL WANT TO CONTINUE
            // RUNNING THE PROGRAM
            while (true) {
                System.out.print("\nDo you want to add again? (Y/N): ");
                answer = scan.next();
                if (answer.equalsIgnoreCase("Y")) {
                    // CONTINUE THE LOOP OR THE PROGRAM
                    break;
                } else if (answer.equalsIgnoreCase("N")) {
                    // EXIT THE PROGRAM
                    System.out.println("Exiting the program.");
                    return;
                } else {
                    // MAKE SURE THAT THE USER
                    // PROVIDES VALID INPUT
                    System.out.println("Please provide a valid option.");
                }
            }
    }   // CONTINUE THE PROGRAM IF THE USER WANTS TO CONTINUE
        while (true);
    }
}

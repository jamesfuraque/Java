// Jam Furaque -    101382608
// Tenzin Thinley - 101454475
// Carl Trinidad -  101425882
// Kate Labis -     101413112
// Sandra Antony -  101416330

import java.util.Scanner;
import java.util.InputMismatchException;
public class ConnectFourPlayer {
    private char symbol;
    private Scanner scanner;
    private String name;

    // CONSTRUCTOR
    public ConnectFourPlayer(char symbol, Scanner scanner, String name) {
        this.symbol = symbol;
        this.scanner = scanner;
        this.name = name;
    }

    // GETTER FOR NAME
    public String getName() {
        return name;
    }

    // METHOD FOR PLAYER'S MOVE
    public void makeMove(ConnectFourBoard board) {
        // ASK THE PLAYER WHICH COLUMN THEY WOULD PUT THEIR DISC OR SYMBOL
        System.out.println(name + "'s turn. Enter column number (1-7):");
        // INDICATES INVALID MOVE.
        int col = -1;
        // LOOP WHILE VALID COLUMN NUMBER IS ENTERED
        while (col == -1) {
            try {
                // SUBTRACT 1 TO CONVERT TO ARRAY'S INDEX
                col = scanner.nextInt() - 1;

                // CHECK IF THE CHOSEN COLUMN IS VALID
                if (!board.isValidMove(col)) {
                    // IF NOT VALID, REMIND THE PLAYER.
                    System.out.println("Invalid move. Please try again.");
                    col = -1;
                }
            } catch (InputMismatchException e) {
                // IF THE INPUT FROM PLAYER IS NOT INTEGER, CATCH IT.
                // INFORM THE USER TO TRY AGAIN.
                System.out.println("Invalid entry. Please try again.");

                // CLEAR THE INVALID INPUT FROM SCANNER
                // PREVENTING INFINITE LOOP
                scanner.next();
            }
        }
        // IF VALID MOVE IS CHOSEN, SET THE CURRENT PLAYER'S SYMBOL TO BOARD.
        board.setCurrentPlayer(symbol);

        // PUT THE PLAYER'S DISC TO THE CHOSEN COLUMN.
        board.dropDisc(col, symbol);
    }
}

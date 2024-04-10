// Jam Furaque -    101382608
// Tenzin Thinley - 101454475
// Carl Trinidad -  101425882
// Kate Labis -     101413112
// Sandra Antony -  101416330

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // SETUP OUR SCANNER.
        Scanner scanner = new Scanner(System.in);

        // INTRODUCTION TO OUR GAME
        System.out.println("Welcome to Connect Four!\n");

        // VARIABLE TO CONTROL WHETHER WE CONTINUE PLAYING OR NOT.
        boolean continuePlaying = true;
        while (continuePlaying) {

            // USERS SELECTS THE GAME MODE
            System.out.println("Select game mode:");
            System.out.println("1. 2 player (Human vs Human)");
            System.out.println("2. 1 player (Human vs AI)");
            int choice = scanner.nextInt();

            // BASE ON USER'S INPUT.
            // SELECT CORRESPONDING MODE OF THE GAME.
            if (choice == 1) {                  // OPTION 1:
                playHumanVsHuman(scanner);      // HUMAN VS HUMAN
            } else if (choice == 2) {           // OPTION 2:
                playHumanVsAI(scanner);         // HUMAN VS AI
            } else {

                // IF CHOICE IS INVALID, EXIT.
                System.out.println("Invalid choice. Exiting...");
                continue;
            }

            // AFTER EACH GAME, ASK THE USER
            // IF THEY WANT TO CONTINUE PLAYING OR EXIT THE PROGRAM.
            System.out.print("Do you wish to continue playing? (Y/N): ");
            char playAgainChoice = scanner.next().toLowerCase().charAt(0);
            while (playAgainChoice != 'y' && playAgainChoice != 'n') {
                // IF USER CHOSE DIFFERENT CHOICE, PROMPT THEM INVALID.
                System.out.println("Invalid input. Please enter 'Y' to continue or 'N' to exit.");
                System.out.print("Do you wish to continue playing? (Y/N): ");
                playAgainChoice = scanner.next().toLowerCase().charAt(0);
            }

            continuePlaying = (playAgainChoice == 'y');
        }
        // INFORM USERS THAT PROGRAM IS EXITING.
        System.out.println("Thank you for playing Connect 4!\nSee you next time...");
        scanner.close();
    }

    // HUMAN VS HUMAN MODE.
    private static void playHumanVsHuman(Scanner scanner) {
        // CREATE NEW CONNECT 4 BOARD INSTANCE.
        ConnectFourBoard board = new ConnectFourBoard();
        // DISPLAY EMPTY BOARD.
        board.displayBoard();

        // GET PLAYER 1'S NAME AND SYMBOL.
        System.out.print("Enter Player 1's name: ");
        String player1Name = scanner.next();
        System.out.print(player1Name + ", choose your symbol (R/Y): ");
        char player1Symbol = scanner.next().toUpperCase().charAt(0);

        // ENSURE PLAYER 1 IS CHOOSING VALID SYMBOL.
        while (player1Symbol != 'R' && player1Symbol != 'Y') {
            System.out.println("Invalid symbol choice. Please choose 'R' or 'Y'.");
            System.out.print(player1Name + ", choose your symbol (R/Y): ");
            player1Symbol = scanner.next().toUpperCase().charAt(0);
        }

        // CREATE PLAYER 1 INSTANCE.
        ConnectFourPlayer player1 = new ConnectFourPlayer(player1Symbol, scanner, player1Name);

        // GET PLAYER 2'S NAME
        System.out.print("Enter Player 2's name: ");
        String player2Name = scanner.next();

        char player2Symbol;
        do {
            System.out.print(player2Name + ", choose your symbol (R/Y): ");
            player2Symbol = scanner.next().toUpperCase().charAt(0);

            // CHECK IF PLAYER 2'S SYMBOL IS THE SAME WITH PLAYER 1.
            // IF SYMBOL IS SIMILAR, LET THEM CHOOSE THE OTHER ONE.
            if (player2Symbol == player1Symbol) {
                System.out.println("Symbol already taken. Choose another one.");
            }
        } while (player2Symbol == player1Symbol);

        // CREATE PLAYER 2 INSTANCE.
        ConnectFourPlayer player2 = new ConnectFourPlayer(player2Symbol, scanner, player2Name);

        // START THE GAME LOOP
        // UNTIL THERE IS A WINNER OR THE BOARD IS FULL
        while (!board.checkWin() && !board.isBoardFull()) {
            // PLAYER 1 MAKES A MOVE & DISPLAYS THE BOARD.
            player1.makeMove(board);
            board.displayBoard();

            // CHECK IF PLAYER 1 HAS WON OR THE BOARD IS FULL
            // IF YES, EXIT THE LOOP.
            if (board.checkWin() || board.isBoardFull()) break;

            // PLAYER 2 MAKES A MOVE & DISPLAYS THE BOARD.
            player2.makeMove(board);
            board.displayBoard();
        }
        // PRINT THE RESULT OF THE GAME.
        printResult(board, player1, player2);
    }


    // HUMAN VS AI MODE.
    private static void playHumanVsAI(Scanner scanner) {
        // CREATE NEW CONNECT 4 BOARD INSTANCE
        ConnectFourBoard board = new ConnectFourBoard();
        // DISPLAY THE EMPTY BOARD.
        board.displayBoard();

        // GET PLAYER'S NAME AND SYMBOL
        System.out.print("Enter your name: ");
        String playerName = scanner.next();
        System.out.print("Choose your symbol (R/Y): ");
        char playerSymbol = scanner.next().charAt(0);

        // CONVERT LOWER CASE R AND Y TO UPPERCASE.
        playerSymbol = Character.toUpperCase(playerSymbol);

        // ENSURE THE SYMBOL IS EITHER R OR Y.
        // IF IT'S NOT PROMPT INVALID.
        while (playerSymbol != 'R' && playerSymbol != 'Y') {
            System.out.println("Invalid symbol choice. Please choose 'R' or 'Y'.");
            System.out.print("Choose your symbol (R/Y): ");
            playerSymbol = scanner.next().charAt(0);
            playerSymbol = Character.toUpperCase(playerSymbol);
        }

        // CREATE HUMAN PLAYER INSTANCE.
        ConnectFourPlayer humanPlayer = new ConnectFourPlayer(playerSymbol, scanner, playerName);

        // CREATE AI PLAYER'S INSTANCE
        // WITH THE OPPOSITE SYMBOL OF HUMAN'S SYMBOL.
        ConnectFourAI aiPlayer = new ConnectFourAI(playerSymbol == 'R' ? 'Y' : 'R');

        // DETERMINE WHO WILL GO FIRST.
        System.out.println("Who will go first?");
        System.out.println("1. " + playerName);
        System.out.println("2. AI");
        int firstMoveChoice = scanner.nextInt();

        // TRACK WHO'S TURN IT IS.
        boolean playerTurn = (firstMoveChoice == 1);

        // START THE GAME LOOP UNTIL THERE IS A WINNER
        // OR THE BOARD IS FULL.
        while (!board.checkWin() && !board.isBoardFull()) {
            if (playerTurn) {

                // HUMAN PLAYER MAKES A MOVE.
                humanPlayer.makeMove(board);
                board.displayBoard();

                // CHECK IF HUMAN PLAYER HAS WON.
                if (board.checkWin()) {
                    System.out.println(playerName + " wins!");
                    return;
                }
            } else {
                // AI PLAYER MAKES A MOVE.
                aiPlayer.makeMove(board);
                board.displayBoard();

                // CHECK IF AI PLAYER HAS WON.
                if (board.checkWin()){
                    System.out.println("AI wins!");
                    return;
                }
            }
            // SWITCH TURNS.
            playerTurn = !playerTurn;
        }

        // CHECK IF IT'S A DRAW.
        if (board.isBoardFull()){
            System.out.println("It's a draw!");
        }

    }

    // PRINTING RESULTS FOR WINNER
    private static void printResult(ConnectFourBoard board, ConnectFourPlayer player1, ConnectFourPlayer player2) {
        if (board.checkWin()) {
            // GET THE WINNING SYMBOL.
            char winningSymbol = board.getCurrentPlayer();

            // DETERMINE THE NAME OF THE WINNER
            // BASED ON WINNING SYMBOL.
            String winnerName = (winningSymbol == 'R') ? player1.getName() : player2.getName();

            // PRINT THE NAME OF WINNER.
            System.out.println(winnerName + " wins!");
        } else if (board.isBoardFull()) {

            // PRINT DRAW IF THERE IS A DRAW.
            System.out.println("It's a draw!");

            // ASK THE USER TO RESTART THE NAME.
            System.out.println("Do you want to restart the game? (yes/no)");

            // READ USER'S INPUT.
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.next();

            // IF USER WANTS TO RESTART THE GAME,
            // CALL MAIN TO RESTART THE GAME.
            if (choice.equalsIgnoreCase("yes")) {
                main(new String[]{});
            } else {
                // IF NOT, EXIT THE GAME.
                System.out.println("Thank you for playing Connect 4!\nSee you next time...");
            }
            scanner.close();
        } else {
            System.out.println("It's a draw!");
        }
    }

}
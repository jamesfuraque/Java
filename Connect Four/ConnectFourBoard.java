// Jam Furaque -    101382608
// Tenzin Thinley - 101454475
// Carl Trinidad -  101425882
// Kate Labis -     101413112
// Sandra Antony -  101416330

public class ConnectFourBoard {

    // WE USE 2 DIMENSIONAL ARRAY BECAUSE CONNECT4 GAME IS A GRID BASED GAME
    // AND ACCESS FOR INDIVIDUAL CELLS IN GRID IS SIMPLE
    // USING ROW AND COLUMN INDICES (board[row][col])
    private char[][] board;
    private int numRows = 6;
    private int numCols = 7;
    private char currentPlayer;

    // CONSTRUCTOR
    public ConnectFourBoard() {
        board = new char[numRows][numCols];
        initializeBoard();
        currentPlayer = 'R';
    }

    // SETUP BOARD PROPERLY WITH EMPTY SPACES
    private void initializeBoard() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // SIMPLE DISPLAY OF OUR BOARD
    public void displayBoard() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                // PRINTS A CHARACTER TO SPECIFIC LINE
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------");

    }

    // METHOD TO CHECK THE WINNER
    public boolean checkWin() {
        // HORIZONTAL WINS
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col <= numCols - 4; col++) {
                if (checkLine(row, col, 0, 1))
                    return true;

            }
        }
        // VERTICAL WINS
        for (int col = 0; col < numCols; col++) {
            for (int row = 0; row <= numRows - 4; row++) {
                if (checkLine(row, col, 1, 0))
                    return true;

            }
        }
        // DIAGONAL WINS : BOTTOM LEFT TO TOP RIGHT
        for (int row = 3; row < numRows; row++) {
            for (int col = 0; col <= numCols - 4; col++) {
                if (checkLine(row, col, -1, 1))
                    return true;

            }
        }
        // DIAGONAL WINS : TOP LEFT TO BOTTOM RIGHT
        for (int row = 0; row <= numRows - 4; row++) {
            for (int col = 0; col <= numCols - 4; col++) {
                if (checkLine(row, col, 1, 1))
                    return true;

            }
        }
        return false;
    }

    // METHOD TO CHECK IF THERE IS A WINNING LINE
    private boolean checkLine(int row, int col, int rowDir, int colDir) {
        // GET THE COLOR OF THE DISC AT THE SPECIFIED POSITION
        char color = board[row][col];

        // IF THE COLOR IS EMPTY (NO DISC AT THIS POSITION) RETURN FALSE
        if (color == ' ')
            return false;

        // ITERATE THROUGH THE NEXT THREE POSITIONS IN THE SPECIFIED DIRECTION
        for (int i = 1; i < 4; i++) {
            // CALCULATE THE ROW AND COLUMN INDICES OF THE NEXT POSITION
            int nextRow = row + i * rowDir;
            int nextCol = col + i * colDir;

            // IF THE COLOR IS NOT THE SAME AS THE ORIGINAL COLOR, RETURN FALSE
            if (board[nextRow][nextCol] != color)
                return false;

        }
        // IF ALL POSITIONS IN THE LINE HAS THE SAME COLOR, RETURN TRUE
        // WE HAVE A WINNER
        return true;
    }

    // METHOD TO CHECK IF THE BOARD IS FULL
    public boolean isBoardFull() {
        for (int col = 0; col < numCols; col++) {
            // IF COLUMN HAS AN EMPTY SPACE, BOARD IS NOT YET FULL
            if (board[0][col] == ' ') {
                return false;
            }
        }
        // IF THERE'S NO MORE SPACE, THE BOARD IS FULL
        return true;
    }

    // GETTER FOR CURRENT PLAYER
    public char getCurrentPlayer() {
        return currentPlayer;
    }
    // SETTER FOR CURRENT PLAYER
    public void setCurrentPlayer(char currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    // GETTER FOR BOARD
    public char[][] getBoard() {
        return board;
    }

    // METHOD TO CHECK IF THE MOVE IS VALID.
    public boolean isValidMove(int col) {
        return col >= 0 && col < numCols && board[0][col] == ' ';
    }

    // METHOD FOR DROPPING THE DISK OR THE SYMBOLS YOU ARE PLAYING
    public void dropDisc(int col, char symbol) {
        // DISK WILL START FROM THE BOTTOM AND GOING UPWARDS
        for (int i = numRows - 1; i >= 0; i--) {
            // IF IT FINDS AN EMPTY CELL, IT PUTS THE DISC.
            if (board[i][col] == ' ') {
                board[i][col] = symbol;
                break;
            }
        }
    }

    // UNDOING THE LAST MOVE OF A PLAYER
    public void undoDrop(int col) {
        for (int i = 0; i < numRows; i++) {
            if (board[i][col] != ' ') {
                board[i][col] = ' ';
                break;
            }
        }
    }

    // GETTER FOR NUMBER OF ROWS.
    public int getNumRows() {
        return numRows;
    }
    // GETTER FOR NUMBER OF COLUMNS
    public int getNumCols() {
        return numCols;
    }
}

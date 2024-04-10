// Jam Furaque -    101382608
// Tenzin Thinley - 101454475
// Carl Trinidad -  101425882
// Kate Labis -     101413112
// Sandra Antony -  101416330

public class ConnectFourAI {
    private char symbol;
    private char opponentSymbol;

    // CONSTRUCTOR
    public ConnectFourAI(char symbol) {
        this.symbol = symbol;
        this.opponentSymbol = (symbol == 'R') ? 'Y' : 'R';
    }

    // METHOD FOR AI'S MOVE
    public void makeMove(ConnectFourBoard board) {
        // 6 REPRESENTS THE LOOKAHEAD DEPTH OF AI
        // INITIALIZE THE ALPHA AND BETA FOR THE MINIMAX ALGORITHM.
        int[] bestMove = minimax(board, 6, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        board.dropDisc(bestMove[1], symbol);
    }

    // METHOD FOR MINIMAX
    // RECURSIVELY EXPLORE POSSIBLE FUTURE GAME STATES TO DETERMINE
    // OPTIMAL MOVE FOR AI.
    private int[] minimax(ConnectFourBoard board, int depth, int alpha, int beta, boolean maximizingPlayer) {
        // INITIALIZE VARIABLE TO STORE BEST MOVE
        int[] bestMove = new int[2];
        // AND SCORE
        int bestScore;

        // DETERMINE SYMBOL OF THE CURRENT PLAYER.
        char currentPlayer = maximizingPlayer ? symbol : opponentSymbol;

        // CHECK IF GAME REACHED MAXIMUM DEPTH
        if (depth == 0 || board.checkWin() || board.isBoardFull()) {
            //IF YES, EVALUATE THE CURRENT BOARD STATE.
            int score = evaluate(board);
            return new int[]{score, -1};
        }

        // IF MAXIMIZING PLAYER'S TURN
        if (maximizingPlayer) {
            // INITIALIZE BEST SCORE TO NEGATIVE INFINITY
            bestScore = Integer.MIN_VALUE;

            // ITERATE THROUGH EACH COLUMN OF BOARD.
            for (int col = 0; col < board.getNumCols(); col++) {
                // CHECK IF MOVE IS VALID.
                if (board.isValidMove(col)) {
                    // DROP THE MAXIMIZING PLAYER'S SYMBOL TO THE BOARD.
                    board.dropDisc(col, symbol);

                    // RECURSIVELY CALL MINIMAX FOR NEXT DEPTH
                    // TOGGLE TO THE MAXIMIZING PLAYER'S TURN
                    int score = minimax(board, depth - 1, alpha, beta, false)[0];

                    // UNDO THE MOVE TO REVERT THE BOARD STATE.
                    board.undoDrop(col);
                    // UPDATE THE SCORE AND MOVE IF MOVE LEADS TO BETTER OUTCOME.
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = score;    // UPDATE THE SCORE IN THE bestMove ARRAY
                        bestMove[1] = col;      // UPDATE THE COLUMN INDEX IN THE bestMove ARRAY
                    }

                    // UPDATE THE ALPHA WITH THE MAXIMUM OF ITS CURRENT VALUE
                    alpha = Math.max(alpha, score);
                    // PERFORM ALPHA-BETA PRUNING
                    // IF BETA IS LESS THAN OR EQUAL TO ALPHA.
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
        } else {
            // IF THE MAXIMIZING PLAYER'S TURN.
            // INITIALIZE BEST SCORE TO POSITIVE INFINITY.
            bestScore = Integer.MAX_VALUE;

            // ITERATE THROUGH EACH COLUMN ON THE BOARD.
            for (int col = 0; col < board.getNumCols(); col++) {

                // CHECK IF THE MOVE IS VALID FOR THE CURRENT COLUMN.
                if (board.isValidMove(col)) {
                    // DROP THE MINIMIZING PLAYER'S SYMBOL TO THE BOARD.
                    board.dropDisc(col, opponentSymbol);

                    // RECURSIVELY CALL MINIMAX FOR THE NEXT DEPTH TOGGLE
                    // TO THE MAXIMIZING PLAYER'S TURN.
                    int score = minimax(board, depth - 1, alpha, beta, true)[0];

                    // UNDO THE MOVE TO REVERT THE BOARD STATE.
                    board.undoDrop(col);

                    // UPDATE THE SCORE AND MOVE IF MOVE LEADS TO BETTER OUTCOME.
                    if (score < bestScore) {
                        bestScore = score;
                        bestMove[0] = score;    // UPDATE THE SCORE IN THE bestMove ARRAY
                        bestMove[1] = col;      // UPDATE THE COLUMN INDEX IN THE bestMove ARRAY
                    }

                    // UPDATE THE BETA WITH THE MINIMUM OF ITS CURRENT VALUE
                    beta = Math.min(beta, score);
                    // PERFORM ALPHA-BETA PRUNING
                    // IF BETA IS LESS THAN OR EQUAL TO ALPHA
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
        }
        // RETURN THE BEST MOVE FOUND
        // ALONG WITH ITS SCORE.
        return bestMove;
    }

    private int evaluate(ConnectFourBoard board) {
        int score = 0;
        // GET THE SYMBOL OF THE CURRENT PLAYER.
        char currentPlayer = board.getCurrentPlayer();
        // ASSUME AI'S SYMBOL IS STORED IN THE CLASS.
        char aiSymbol = this.symbol;

        // CHECK IF THE GAME HAS BEEN WON
        // AND THE WINNER IS AI
        if (board.checkWin() && currentPlayer == aiSymbol) {
            // AI WINS
            // ASSIGN A HIGH SCORE
            score = 1000;
        } else if (board.checkWin() && currentPlayer != aiSymbol) {
            // HUMAN WINS
            // ASSIGN A LOW SCORE IF HUMAN WINS.
            score = -1000;
        } else {
            // AI PRIORITIZE MOVE THAT COULD LED TO WIN
            char opponentSymbol = (aiSymbol == 'R') ? 'Y' : 'R';
            // ADD POINTS FOR POTENTIAL WINNING MOVE
            score += countPotentialWins(board, aiSymbol) * 100;
            // DEDUCT POINTS IF OPPONENT HAS POTENTIAL WINNING MOVE
            score -= countPotentialWins(board, opponentSymbol) * 100;
            score += evaluateStrategicPositioning(board, aiSymbol);
        }
        return score;
    }


    // METHOD TO COUNT THE NUMBER OF POTENTIAL WINNING MOVE
    private int countPotentialWins(ConnectFourBoard board, char playerSymbol) {
        int potentialWins = 0;

        // ITERATE THROUGH EACH COLUMN IN THE BOARD.
        for (int col = 0; col < board.getNumCols(); col++) {
            // CHECK IF CURRENT COLUMN HAS SPACE FOR NEW DISC
            if (board.isValidMove(col)) {
                // GET THE ROW WHERE THE NEW DISC WOULD LAND
                int row = getNextAvailableRow(board, col);

                // CHECK IF PLACING A DISC IN THIS POSITION
                // COULD LEAD TO POTENTIAL WIN
                if (isPotentialWin(board, row, col, playerSymbol)) {
                    potentialWins++;
                }
            }
        }
        return potentialWins;
    }

    // HELPER METHOD TO GET THE NEXT AVAILABLE ROW IN A COLUMN
    // WHERE DISC CAN BE DROPPED.
    private int getNextAvailableRow(ConnectFourBoard board, int col) {
        // ITERATE FROM THE BOTTOM ROW TO THE TOP ROW
        // OF THE SPECIFIED COLUMN
        for (int row = board.getNumRows() - 1; row >= 0; row--) {

            // CHECK IF THE CELL AT THE CURRENT ROW AND COLUMN
            // IS EMPTY.
            if (board.getBoard()[row][col] == ' ') {
                // IF EMPTY, RETURN THE ROW AS
                // NEXT AVAILABLE ROW.
                return row;
            }
        }
        // IF THE LOOP HAS ENDED WITHOUT EMPTY CELL
        // IT MEANS COLUMN IS FULL.
        return -1;
    }


    // HELPER METHOD TO CHECK IF PLACING A DISC TO SPECIFIC POSITION
    // LEADS TO POTENTIAL WIN.
    private boolean isPotentialWin(ConnectFourBoard board, int row, int col, char playerSymbol) {
        // CHECK HORIZONTALLY
        int horizontalCount = countConsecutiveDiscs(board, row, col, 0, 1, playerSymbol);

        // CHECK VERTICALLY
        int verticalCount = countConsecutiveDiscs(board, row, col, 1, 0, playerSymbol);

        // CHECK DIAGONALLY (BOTH DIRECTIONS)
        int diagonalCount1 = countConsecutiveDiscs(board, row, col, 1, 1, playerSymbol);
        int diagonalCount2 = countConsecutiveDiscs(board, row, col, 1, -1, playerSymbol);

        // POTENTIAL WIN OCCURS IF THERE ARE AT LEAST
        // 3 CONSECUTIVE DISCS IN ANY DIRECTION.
        return horizontalCount >= 3 || verticalCount >= 3 || diagonalCount1 >= 3 || diagonalCount2 >= 3;
    }


    // HELPER METHOD TO COUNT
    // CONSECUTIVE DISCS IN A SPECIFIC DIRECTION.
    private int countConsecutiveDiscs(ConnectFourBoard board, int row, int col, int rowDir, int colDir, char playerSymbol) {
        int count = 0;

        // CHECK CONSECUTIVE DISCS IN THE SPECIFIED DIRECTION
        while (row >= 0 && row < board.getNumRows() && col >= 0 && col < board.getNumCols() &&
                board.getBoard()[row][col] == playerSymbol) {
            count++;

            // MOVE TO THE NEXT POSITION
            // IN THE SPECIFIED DIRECTION.
            row += rowDir;
            col += colDir;
        }
        return count;
    }


    // EVALUATE STRATEGIC POSITIONING ON THE BOARD
    // FOR THE GIVEN PLAYER.
    private int evaluateStrategicPositioning(ConnectFourBoard board, char playerSymbol) {
        int strategicScore = 0;

        // ITERATE THROUGH EACH POSITION ON THE BOARD.
        for (int row = 0; row < board.getNumRows(); row++) {
            for (int col = 0; col < board.getNumCols(); col++) {
                if (board.getBoard()[row][col] == ' ') {

                    // CALCULATE THE STRATEGIC SCORE FOR THE CURRENT POSITION.
                    strategicScore += calculatePositionScore(board, row, col, playerSymbol);
                }
            }
        }
        return strategicScore;
    }

    // HELPER METHOD DO CALCULATE THE STRATEGIC SCORE
    // FOR SPECIFIC POSITION.
    private int calculatePositionScore(ConnectFourBoard board, int row, int col, char playerSymbol) {
        int positionScore = 0;

        // CHECK HORIZONTALLY
        int horizontalScore = calculateDirectionalScore(board, row, col, 0, 1, playerSymbol);
        // CHECK VERTICALLY
        int verticalScore = calculateDirectionalScore(board, row, col, 1, 0, playerSymbol);
        // CHECK DIAGONALLY (BOTH DIRECTIONS)
        int diagonalScore1 = calculateDirectionalScore(board, row, col, 1, 1, playerSymbol);
        int diagonalScore2 = calculateDirectionalScore(board, row, col, 1, -1, playerSymbol);

        // SUM UP ALL SCORES IN DIRECTIONS.
        positionScore = horizontalScore + verticalScore + diagonalScore1 + diagonalScore2;
        return positionScore;
    }

    // HELPER METHOD TO CALCULATE THE STRATEGIC SCORE FROM A GIVEN POSITION
    private int calculateDirectionalScore(ConnectFourBoard board, int row, int col, int rowDir, int colDir, char playerSymbol) {
        int score = 0;

        // CHECK IF PLACING DISK IN THIS DIRECTION COULD LEAD TO WIN
        if (hasPotentialWin(board, row, col, rowDir, colDir, playerSymbol)) {
            // ASSIGN HIGHER SCORE FOR POTENTIAL WINNING POSITIONS
            score += 10;
        }
        return score;
    }

    // HELPER METHOD
    private boolean hasPotentialWin(ConnectFourBoard board, int row, int col, int rowDir, int colDir, char playerSymbol) {
        int consecutiveDiscs = 0;
        int emptySpaces = 0;

        // CHECK CONSECUTIVE DISCS AND EMPTY SPACES IN SPECIFIED DIRECTION
        while (row >= 0 && row < board.getNumRows() && col >= 0 && col < board.getNumCols()) {
            if (board.getBoard()[row][col] == playerSymbol) {
                consecutiveDiscs++;
            } else if (board.getBoard()[row][col] == ' ') {
                emptySpaces++;
            } else {
                // STOP CHECKING IF OPPONENT'S DISC IS ENCOUNTERED
                break;
            }
            row += rowDir;
            col += colDir;
        }
        // POTENTIAL WIN OCCURS IF THERE ARE
        // AT LEAST THREE DISCS IN ONE EMPTY SPACE
        return consecutiveDiscs >= 3 && emptySpaces >= 1;
    }
}

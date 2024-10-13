import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static final char EMPTY_CELL = '-';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    private char[][] board;
    private boolean isPlayerXTurn;

    public TicTacToe() {
        board = new char[ROWS][COLS];
        initializeBoard();
        isPlayerXTurn = true;
    }

    private void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (!isGameOver()) {
            printBoard();

            System.out.print((isPlayerXTurn ? "Player X" : "Player O") + ", enter your move (row col): ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;

            if (isValidMove(row, col)) {
                makeMove(row, col);
                changeTurn();
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }

        printBoard();

        if (isDraw()) {
            System.out.println("It's a draw!");
        } else {
            System.out.println((isPlayerXTurn ? "Player O" : "Player X") + " wins!");
        }
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS && board[row][col] == EMPTY_CELL;
    }

    private void makeMove(int row, int col) {
        board[row][col] = isPlayerXTurn ? PLAYER_X : PLAYER_O;
    }

    private void changeTurn() {
        isPlayerXTurn = !isPlayerXTurn;
    }

    private boolean isGameOver() {
        return isWinner() || isDraw();
    }

    private boolean isWinner() {
        // Check rows
        for (int i = 0; i < ROWS; i++) {
            if (board[i][0] != EMPTY_CELL && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < COLS; j++) {
            if (board[0][j] != EMPTY_CELL && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] != EMPTY_CELL && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[0][2] != EMPTY_CELL && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }

        return false;
    }

    private boolean isDraw() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] == EMPTY_CELL) {
                    return false;
                }
            }
        }
        return true;
    }

    private void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < ROWS; i++) {
            System.out.print("| ");
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.play();
    }
}

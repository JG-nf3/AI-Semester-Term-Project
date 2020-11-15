import java.util.ArrayList;
import java.util.List;

public class Game {
    // Current board
    private Board current;

    /**
     * Default constructor, makes a new board with its default constructor
     */
    public Game() {
        current = new Board();
    }

    /**
     * Checks if the game if over
     *
     * @return - true if the game if over, false otherwise
     */
    public boolean isOver() {
        for (byte row = 0; row < 4; row++) {
            for (byte col = 0; col < 4; col++) {
                for (byte dir = 0; dir < 8; dir++) {
                    // Loop through all squares and directions checking for legal moves
                    if (isLegal(row, col, dir)) {
                        // If we ever hit a legal move return false because the game isn't over
                        return false;
                    }
                }
            }
        }
        // If we get here there are no legal moves so return true
        return true;
    }

    /**
     * Move the game to the next turn
     *
     * @param row - the row containing our stones to move
     * @param col - the column containing our stones to move
     * @param dir - the direction we want to move in
     */
    public void nextTurn(byte row, byte col, byte dir) {
        // updates the current board to a new board with the move made
        current = new Board(current.newBoard(row, col, dir));
    }

    public Board getBoard() {
        return current;
    }

    /**
     * Checks whether a move is legal or not
     *
     * @param row - row containing stones
     * @param col - col containing stones
     * @param dir - direction to move in
     * @return - true if move is legal, false otherwise
     */
    public boolean isLegal(byte row, byte col, byte dir) {
        // If validMove returns a nonzero value then its a legal move
        return current.validMove(row, col, dir) != 0;
    }

    /**
     * @return a List of byte arrays that contain legal moves
     * these byte arrays should be of length 3 and contain
     * the row, col, and dir, in that order
     */
    public List<byte[]> getLegalMoves() {
        // List to store the legal moves for returning
        List<byte[]> moves = new ArrayList<>();

        for (byte row = 0; row < 4; row++) {
            for (byte col = 0; col < 4; col++) {
                for (byte dir = 0; dir < 8; dir++) {
                    // Loop through every move and check if the move is legal
                    if (isLegal(row, col, dir)) {
                        // if it is, then put it in the correct byte[] format and add it to our list of moves
                        byte[] move = new byte[3];
                        move[0] = row;
                        move[1] = col;
                        move[2] = dir;
                        moves.add(move);
                    }
                }
            }
        }

        // Return our list of legal moves
        return moves;
    }
}
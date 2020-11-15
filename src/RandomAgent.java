import java.util.List;
import java.util.Random;

public class RandomAgent {
    // Our current game & a random number generator we'll use on every move
    private Game game;
    private final Random rng;

    /**
     * Default constructor
     *
     * @param game - the game we're using
     */
    public RandomAgent(Game game) {
        this.game = game;
        // RNG used for choosing our moves
        this.rng = new Random();
    }

    /**
     * Makes a move
     */
    public void move() {
        // Gets the list of currently legal moves from the game
        List<byte[]> legalMoves = game.getLegalMoves();
        // If there are no legal moves, return
        if (legalMoves.size() < 1) return;
        // Choose a move randomly from the list of legal moves
        byte[] chosenMove = legalMoves.get(rng.nextInt(legalMoves.size()));

        // Move the game to the next turn
        // in getLegalMoves we return a list of byte arrays, which each contain a row, column, and direction
        game.nextTurn(chosenMove[0], chosenMove[1], chosenMove[2]);
    }

    public void setGame(Game game) {
        this.game = game;
    }
}

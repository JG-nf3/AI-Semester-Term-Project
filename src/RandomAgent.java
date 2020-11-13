import java.util.List;
import java.util.Random;

public class RandomAgent {
    private Game game;

    public RandomAgent(Game game) {
        this.game = game;
        System.out.println("random agent instantiated");
    }

    public void move() {
        // RNG used for choosing our move
        Random rng = new Random();

        //System.out.println("RA turn");
        // Gets the list of currently legal moves from the game
        List<byte[]> legalMoves = game.getLegalMoves();
        // If there are no legal moves, don't try to chose one
        if (legalMoves.size() < 1) return;
        //System.out.println(legalMoves.size() + " legal moves available for random agent");
        // Choose a move randomly from the list of legal moves
        byte[] chosenMove = legalMoves.get(rng.nextInt(legalMoves.size()));
        //System.out.println(chosenMove[0] + " " + chosenMove[1] + " " + chosenMove[2]);

        // Move the game to the next turn
        // in getLegalMoves we return a list of byte arrays, which each contain a row, column, and direction
        game.nextTurn(chosenMove[0], chosenMove[1], chosenMove[2]);
    }

    public void setGame(Game game) {
        this.game = game;
    }
}

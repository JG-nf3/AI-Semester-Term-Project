import java.util.List;

public class AIAgent {
    private final Game game;
    private Tree tree;

    public AIAgent(Game game) {
        this.game = game;
        System.out.println("AI agent instantiated");
    }

    public void move() {
        System.out.println("AI turn");
        tree = new Tree(new Node(game.getBoard()));

        // Gets the list of currently legal moves from the game
        List<byte[]> legalMoves = game.getLegalMoves();
        // If there are no legal moves, don't try to chose one
        if (legalMoves.size() < 1) return;
        System.out.println(legalMoves.size() + " legal moves available for ai agent");

        // Choose the best move from the given tree
        byte[] chosenMove = tree.getBestMove();
        System.out.println(chosenMove[0] + " " + chosenMove[1] + " " + chosenMove[2]);

        // Move the game to the next turn
        // in getLegalMoves we return a list of byte arrays, which each contain a row, column, and direction
        game.nextTurn(chosenMove[0], chosenMove[1], chosenMove[2]);
    }
}

public class AIAgent {

    private Game game;
    private Tree tree;

    /**
     * Default constructor
     *
     * @param game - the game we're using
     */
    public AIAgent(Game game) {
        this.game = game;
    }

    /**
     * Makes a move
     */
    public void move() {
        // Create a new game tree with the current board state as the root
        tree = new Tree(new Node(game.getBoard()));

        // Choose the best move from the given tree
        byte[] chosenMove = tree.getBestMove2();

        // Move the game to the next turn
        // in getBestMove we return a list of byte arrays, which each contain a row, column, and direction
        game.nextTurn(chosenMove[0], chosenMove[1], chosenMove[2]);
    }

    public void setGame(Game game) {
        this.game = game;
    }
}

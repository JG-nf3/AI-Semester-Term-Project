public class AIAgent {

    private Game game;
    private Tree tree;
    private int totalNodes;
    private int totalDepth;
    private int totalPruned;

    /**
     * Default constructor
     *
     * @param game - the game we're using
     */
    public AIAgent(Game game) {
        this.game = game;
        totalNodes = 0;
        totalDepth = 0;
        totalPruned = 0;
    }

    /**
     * Makes a move
     */
    public void move() {
        // Create a new game tree with the current board state as the root
        tree = new Tree(new Node(game.getBoard()));

        // Choose the best move from the given tree
        byte[] chosenMove = tree.getBestMove();

        Node_Counter temp1 = getCounter();
        Node_Counter temp2 = getFullCounter();
        System.out.println("Expanded " + temp1.getVisitedNodeTotal() + " nodes.");
        System.out.println("Max depth is " + temp1.getMaxDepth() + ".");
        System.out.println("Pruned " + (temp2.getVisitedNodeTotal() - temp1.getVisitedNodeTotal()) + " nodes.");

        totalNodes += temp1.getVisitedNodeTotal();
        totalDepth += temp1.getMaxDepth();
        totalPruned += (temp2.getVisitedNodeTotal() - temp1.getVisitedNodeTotal());


        // Move the game to the next turn
        // in getBestMove we return a list of byte arrays, which each contain a row, column, and direction
        game.nextTurn(chosenMove[0], chosenMove[1], chosenMove[2]);
    }

    public void setGame(Game game) {
        this.game = game;
        totalNodes = 0;
        totalDepth = 0;
        totalPruned = 0;
    }

    public Node_Counter getCounter() {
        return tree.getCounter();
    }

    public Node_Counter getFullCounter() {
        return tree.getFullCounter();
    }

    public void printTotals() {
        System.out.println("Total expanded " + totalNodes + " nodes.");
        System.out.println("Total depth is " + totalDepth + ".");
        System.out.println("Total pruned " + totalPruned + " nodes.");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }
}

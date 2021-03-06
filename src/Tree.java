public class Tree {
    private final Node root;
    private final int defaultDepth;

    /**
     * Default constructor
     *
     * @param root - the root node for our tree
     */
    public Tree(Node root) {
        this.root = root;
        // The depth of our tree will be 4
        defaultDepth = 4;
    }

    /**
     * Generates one step of the tree from the given node
     *
     * @param depth        - max depth
     * @param currentDepth - current depth
     * @param currentNode  - node to expand
     */
    public void generateOneStepForFullTree(int depth, int currentDepth, Node currentNode) {
        // If the depth we are currently at is less than our total depth then we want to generate its children
        if (currentDepth < depth) {
            if (!currentNode.wasExpanded()) {
                // Make sure the current node hasn't been expanded already and make its children
                currentNode.makeChildren();
            }

            // Loop through each child and recursively generate another step out
            for (Node n : currentNode.getChildren()) {
                generateOneStepForFullTree(depth, currentDepth + 1, n);
            }
        }
        root.countAdd1();
    }

    /**
     * Generate the tree from the root to the default depth
     * This won't handle any pruning
     */
    public void generateToDepthForFullTree() {
        generateOneStepForFullTree(defaultDepth, 0, root);
    }

    /**
     * Generates the tree, using minimax w/ alpha beta
     */
    public void generateToDepth() {
        root.makeChildrenOfRoot(defaultDepth, 0);
    }

    /**
     * Gets the current best move
     * Does the Min-Max algorithm
     *
     * @return - a 3 length byte array containing the row, column, and direction of the best move
     */
    public byte[] getBestMove() {
        // Generate the tree to the default depth
        generateToDepth();

        int numOfChildren = root.getChildren().size();
        for (int i = 0; i < numOfChildren; i++) {
            if (root.getChildren().get(i).getBeta() == root.getAlpha()) {
                return root.getChildren().get(i).getMove();
            }
        }

        return null;
    }

    /**
     * Returns the Node_counter with pruning
     */
    public Node_Counter getCounter() {
        return root.getCounter();
    }

    /**
     * Returns the Node_counter without pruning
     */
    public Node_Counter getFullCounter() {
        Tree temp = new Tree(new Node(root.getBoard()));
        temp.generateToDepthForFullTree();
        return temp.getCounter();
    }
}

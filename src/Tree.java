public class Tree {
    private final Node root;
    private int defaultDepth;

    public Tree(Node root) {
        this.root = root;
        defaultDepth = 4;
    }

    /**
     * Generates one step of the tree from the given node
     *
     * @param depth        - max depth
     * @param currentDepth - current depth
     * @param currentNode  - node to expand
     */
    public void generateOneStep(int depth, int currentDepth, Node currentNode) {
        // If the depth we are currently at is less than our total depth then we want to generate its children
        if (currentDepth < depth) {
            if (!currentNode.getHasBeenExpanded()) {
                // Make sure the current node hasn't been expanded already and make its children
                currentNode.makeChildren();
            }

            // Loop through each child and recursively generate another step out
            for (Node n : currentNode.getChildren()) {
                generateOneStep(depth, currentDepth + 1, n);
            }
        }

        currentNode.generateScore();
    }

    /**
     * Generate the tree from the root to the default depth
     */
    public void generateToDepth() {
        generateOneStep(defaultDepth, 0, root);
    }

    /**
     * Gets the current best move
     *
     * @return - a 3 length byte array containing the row, column, and direction of the best move
     */
    public byte[] getBestMove() {
        // Generate the tree to the default depth
        generateToDepth();

        int numOfChildren = root.getChildren().size();

        for (int i = 0; i < numOfChildren; i++) {
            if (root.getChildren().get(i).getScore() == root.getScore()) {
                if (root.getScore() >= 1000) {
                    defaultDepth = 24;
                }
                return root.getChildren().get(i).getMove();
            }
        }

        return null;
    }
}

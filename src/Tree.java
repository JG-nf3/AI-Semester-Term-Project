public class Tree {
    private final Node root;
    private int defaultDepth;

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
    }

    /**
     * Generate one step of the tree from the given node
     *
     * @param depth        - max depth
     * @param currentDepth - current depth
     * @param currentNode  - node to expand
     */
    public void generateOneStep(int depth, int currentDepth, Node currentNode) {
        // If the depth we are currently at is less than our total depth then we want to generate its children
        if (currentDepth < depth) {
            if (!currentNode.wasExpanded()) {
                // Make sure the current node hasn't been expanded already and make its children
                currentNode.makeChildren();
            }

            // Loop through each child and recursively generate another step out
            for (Node n : currentNode.getChildren()) {
                generateOneStep(depth, currentDepth + 1, n);
            }
        }

    }


    /**
     * Generate the tree from the root to the default depth
     */
    public void generateToDepthForFullTree() {
        generateOneStepForFullTree(defaultDepth, 0, root);
    }

    public void generateToDepth() {
        root.makeChildrenOfRoot(defaultDepth, 0);
    }

    /**
     * Gets the current best move
     *
     * @return - a 3 length byte array containing the row, column, and direction of the best move
     */
    public byte[] getBestMove2() {
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
     * Gets the current best move
     *
     * @return - a 3 length byte array containing the row, column, and direction of the best move
     */
    public byte[] getBestMove() {
        // Generate the tree to the default depth
        generateToDepthForFullTree();

        root.generateScoreForFullTree(Integer.MIN_VALUE, Integer.MAX_VALUE);


        int numOfChildren = root.getChildren().size();

        for (int i = 0; i < numOfChildren; i++) {

            if (root.getChildren().get(i).getScore() == root.getScore()) {
                return root.getChildren().get(i).getMove();

            }
        }

        return null;
    }
}

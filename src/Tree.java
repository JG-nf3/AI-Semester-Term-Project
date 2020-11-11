public class Tree {
    private final Node root;
    private int defaultDepth;

    public Tree(Node root) {
        this.root = root;
        defaultDepth = 4;
    }

    public void generateOneStep(int depth, int currentDepth, Node currentNode) {
        if (currentDepth < depth) {
            if (!currentNode.getHasBeenExpanded()) {
                currentNode.makeChildren();
            }

            int numOfChildren = currentNode.getChildren().size();
            for (int i = 0; i < numOfChildren; i++) {
                generateOneStep(depth, currentDepth + 1, currentNode.getChildren().get(i));
            }
        }

        currentNode.generateScore();
    }

    public void generateToDepth() {
        generateOneStep(defaultDepth, 0, root);
    }

    public void generateScores(Node currentNode) {
        if (!currentNode.isLeaf()) {
            int numOfChildren = currentNode.getChildren().size();
            for (int i = 0; i < numOfChildren; i++) {
                generateScores(currentNode.getChildren().get(i));
            }
        }
        currentNode.generateScore();
    }

    public byte[] getBestMove() {
        generateToDepth();
        //generateScores(root);

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

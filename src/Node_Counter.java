public class Node_Counter {
    private int visitedNodeTotal;
    private int maxDepth;

    /**
     * Default constructor, set our visitedNodeTotal and maxDepth to 0
     */
    public Node_Counter() {
        visitedNodeTotal = 0;
        maxDepth = 0;
    }

    /**
     * when called adds 1 to visited nodes
     */
    public void addToVisitedNodeTotal() {
        visitedNodeTotal++;
    }

    /**
     * If the passed in depth is greater than our maxDepth increase it
     *
     * @param depth - the new depth
     */
    public void updateMaxDepth(int depth) {
        if (depth > maxDepth) {
            maxDepth = depth;
        }
    }

    /**
     * @return visitedNodeTotal
     */
    public int getVisitedNodeTotal() {
        return visitedNodeTotal;
    }

    /**
     * @return maxDepth
     */
    public int getMaxDepth() {
        return maxDepth;
    }
}
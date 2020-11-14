public class Node_Counter {
    private int visitedNodeTotal;
    private int maxDepth;

    public Node_Counter() {
        visitedNodeTotal = 0;
    }

    public void addToVisitedNodeTotal(){ visitedNodeTotal++; }



    public void updateMaxDepth(int depth){
        if(depth > maxDepth){
            maxDepth = depth;
        }
    }

    public int getVisitedNodeTotal(){
        return visitedNodeTotal;
    }

    public int getMaxDepth(){
        return maxDepth;
    }
}

import java.util.ArrayList;

public class Node_Counter {
    private int visitedNodeTotal;
    private int prunedNodeTotal;

    public Node_Counter() {
        visitedNodeTotal = 0;
        prunedNodeTotal = 0;
    }

    public void addToVisitedNodeTotal(int num){
        visitedNodeTotal += num;
    }

    public void addToPrunedNodeTotal(){
        prunedNodeTotal++;
    }

    public int getVisitedNodeTotal(){
        return visitedNodeTotal;
    }

    public int getPrunedNodeTotal(){
        return prunedNodeTotal;
    }
}

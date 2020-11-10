import java.util.ArrayList;
import java.util.List;

public class Node {
    private Node parent;
    private ArrayList<Node> children;
    private byte depth;
    private Board state;
    private int score;
    private boolean hasBeenExpanded;
    private byte[] move;

    public Node(Node parent, Board state, byte[] move) {
        this.parent = parent;
        this.state = state;
        this.move = move;
        depth = parent.getDepth();
        //java wants this to be two lines not 1
        depth += 1;
        children = new ArrayList<Node>(200);
        hasBeenExpanded = false;
    }

    public Node(Board state) {
        parent = null;
        this.state = state;
        move = null;
        depth = 0;
        children = new ArrayList<Node>(20);
        hasBeenExpanded = false;
    }

    public byte[] getMove() {
        return move;
    }

    public byte getDepth(){
        return depth;
    }


    public int getScore(){
        return score;
    }

    public void generateScore(){
        if(isLeaf()){
            score = state.getScore();
        }
        else{
            //even depth means max node
            if(depth % 2 == 0){
                int max = children.get(0).getScore();
                int numOfChildern = children.size();
                for(int i = 1; i < numOfChildern; i++){
                    if(children.get(i).getScore() > max){
                        max = children.get(i).getScore();
                    }
                }
                score = max;
            }
            else{
                //even depth means min node
                int min = children.get(0).getScore();
                int numOfChildern = children.size();
                for(int i = 1; i < numOfChildern; i++){
                    if(children.get(i).getScore() < min){
                        min = children.get(i).getScore();
                    }
                }
                score = min;

            }

        }



    }

    public Board getBoard(){
        return state;
    }

    public boolean getHasBeenExpanded() {
        return hasBeenExpanded;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void makeChildern(){
        List<byte[]> moves = getLegalMoves();
        for(int i = 0; i < moves.size(); i++){
            byte[] temp = moves.get(i);
            children.add(new Node(this, state.newBoard(temp[0], temp[1], temp[2]), temp));
        }
        hasBeenExpanded = true;
        if(children.size() != 0){ state=null; }
    }

    public boolean isLeaf(){
        return children.size() == 0;
    }

    public List<byte[]> getLegalMoves() {
        List<byte[]> moves= new ArrayList<byte[]>();

        for (byte row = 0; row < 4; row++) {
            for (byte col = 0; col < 4; col++) {
                for (byte dir = 0; dir < 8; dir++) {
                    if (state.validMove(row, col, dir) != 0) {
                        byte[] move = new byte[3];
                        move[0] = row;
                        move[1] = col;
                        move[2] = dir;
                        moves.add(move);
                    }
                }
            }
        }

        return moves;
    }
}

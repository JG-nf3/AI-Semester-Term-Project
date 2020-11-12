import java.util.ArrayList;
import java.util.List;

public class Node {
    private final Node parent;
    private final ArrayList<Node> children;
    private byte depth;
    private Board state;
    private int score;
    private boolean hasBeenExpanded;
    private final byte[] move;

    public Node(Node parent, Board state, byte[] move) {
        this.parent = parent;
        this.state = state;
        this.move = move;
        depth = parent.getDepth();
        //java wants this to be two lines not 1
        depth += 1;
        children = new ArrayList<>(200);
        hasBeenExpanded = false;
    }

    public Node(Board state) {
        parent = null;
        this.state = state;
        move = null;
        depth = 0;
        children = new ArrayList<>(20);
        hasBeenExpanded = false;
    }

    public byte[] getMove() {
        return move;
    }

    public byte getDepth() {
        return depth;
    }

    public int getScore() {
        return score;
    }

    /**
     * Generates the score for the current node using the Minimax algorithm
     * TODO: implement alpha beta pruning
     */
    public void generateScore() {
        if (isLeaf()) {
            score = state.getScore();
        } else {
            // even depth means max node
            if (depth % 2 == 0) {
                int max = children.get(0).getScore();
                for (Node n : children) {
                    if (n.getScore() > max) {
                        max = n.getScore();
                    }
                }
                score = max;
            } else {
                // odd depth means min node
                int min = children.get(0).getScore();
                for (Node n : children) {
                    if (n.getScore() < min) {
                        min = n.getScore();
                    }
                }
                score = min;
            }
        }
    }

    public Board getBoard() {
        return state;
    }

    public boolean getHasBeenExpanded() {
        return hasBeenExpanded;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    /**
     * Makes the child nodes for us, the successor function
     */
    public void makeChildren() {
        // Get all the legal moves from the current node
        List<byte[]> moves = getLegalMoves();
        // For each move that we have, we need to add a new child with the updated board state
        for (byte[] temp : moves) {
            children.add(new Node(this, state.newBoard(temp[0], temp[1], temp[2]), temp));
        }
        // After we've made its children we can mark our current node as having been expanded
        hasBeenExpanded = true;
        // If we have a nonzero amount of children, we delete the state of the current node
        if (children.size() != 0) {
            state = null;
        }
    }

    public boolean isLeaf() {
        return children.size() == 0;
    }

    /**
     * Gets all legal moves from the current nodes state
     *
     * @return - a list of 3 length byte arrays containing the moves
     */
    public List<byte[]> getLegalMoves() {
        List<byte[]> moves = new ArrayList<>();

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

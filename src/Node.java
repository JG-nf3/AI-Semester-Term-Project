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
    private int alpha;
    private int beta;
    private Node_Counter counter;

    public Node(Node parent, Board state, byte[] move, Node_Counter counter) {
        this.parent = parent;
        this.state = state;
        this.move = move;
        depth = parent.getDepth();
        //java wants this to be two lines not 1
        depth += 1;
        children = new ArrayList<>(200);
        hasBeenExpanded = false;

        alpha = Integer.MIN_VALUE;
        beta = Integer.MAX_VALUE;

        this.counter = counter;
    }

    public Node(Board state) {
        parent = null;
        this.state = state;
        move = null;
        depth = 0;
        children = new ArrayList<>(20);
        hasBeenExpanded = false;
        counter = new Node_Counter();
    }

    public byte[] getMove() {
        return move;
    }

    public byte getDepth() {
        return depth;
    }

    /**
     * Generates the score for the current node using the Minimax algorithm
     * TODO: implement Agreek Bgreek pruning
     */
    public int generateScoreForFullTree(int Agreek, int Bgreek) {
        if (isLeaf()) {
            score = state.getScore();
            return score;
        } else {
            // even depth means max node
            if (depth % 2 == 0) {
                int best = Integer.MIN_VALUE;

                for (Node n : children) {
                    int val = n.generateScoreForFullTree(Agreek, Bgreek);
                    best = Math.max(best, val);
                    Agreek = Math.max(Agreek, best);

                    if (Bgreek <= Agreek)
                        break;
                }
                score = best;
                return score;
            } else {
                // odd depth means min node
                int best = Integer.MAX_VALUE;

                for (Node n : children) {
                    int val = n.generateScoreForFullTree(Agreek, Bgreek);
                    best = Math.min(best, val);
                    Bgreek = Math.min(Bgreek, best);

                    if (Bgreek <= Agreek)
                        break;
                }
                score = best;
                return score;
            }
        }
    }

    public Board getBoard() {
        return state;
    }

    public boolean wasExpanded() {
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
            children.add(new Node(this, state.newBoard(temp[0], temp[1], temp[2]), temp, counter));
        }
        // After we've made its children we can mark our current node as having been expanded
        hasBeenExpanded = true;
        // If we have a nonzero amount of children, we delete the state of the current node
        if (children.size() != 0) {
            state = null;
        }
    }


    public void makeChildrenOfRoot(int depth, int currentDepth) {
        //System.out.println("make move");

        alpha = Integer.MIN_VALUE;


        // Get all the legal moves from the current node
        List<byte[]> moves = getLegalMoves();
        int size = moves.size();
        for (int i = 0; i < size; i++) {
            byte[] temp = moves.get(i);
            Node tempNode = new Node(this, state.newBoard(temp[0], temp[1], temp[2]), temp, counter);
            tempNode.makeChildrenMin(depth, currentDepth + 1);
            children.add(tempNode);
            if (children.get(i).getBeta() > alpha) {
                alpha = children.get(i).getBeta();
            }
        }


        // After we've made its children we can mark our current node as having been expanded
        //hasBeenExpanded = true;
        counter.addToVisitedNodeTotal();
    }


    public void makeChildrenMin(int depth, int currentDepth) {
        //System.out.println("    call MIN");
        // case of leaf node stop making children
        if (currentDepth == depth) {
            beta = state.getScore();
            counter.updateMaxDepth(currentDepth);
        } else {
            // Get all the legal moves from the current node
            List<byte[]> moves = getLegalMoves();
            int size = moves.size();

            // if size is zero then is terminal node
            if (size == 0) {
                beta = state.getScore();
                counter.updateMaxDepth(currentDepth);
            }
            // else keep going down tree
            else {
                for (int i = 0; beta > parent.alpha && i < size; i++) {
                    byte[] temp = moves.get(i);
                    Node tempNode = new Node(this, state.newBoard(temp[0], temp[1], temp[2]), temp, counter);
                    tempNode.makeChildrenMax(depth, currentDepth + 1);
                    children.add(tempNode);
                    if (children.get(i).getAlpha() < beta) {
                        beta = children.get(i).getAlpha();
                    }
                }
            }


            // After we've made its children we can mark our current node as having been expanded
            hasBeenExpanded = true;
        }


        // update alpha of parent
        if (beta > parent.alpha) {
            parent.alpha = beta;
        }

        // null the state to save space in tree
        state = null;
        counter.addToVisitedNodeTotal();
    }


    public void makeChildrenMax(int depth, int currentDepth) {
        // case of leaf node stop making children
        if (currentDepth == depth) {
            alpha = state.getScore();
            counter.updateMaxDepth(currentDepth);
        } else {
            // Get all the legal moves from the current node
            List<byte[]> moves = getLegalMoves();
            int size = moves.size();

            // if size is zero then is terminal node
            if (size == 0) {
                alpha = state.getScore();
                counter.updateMaxDepth(currentDepth);
            }
            // else keep going down tree
            else {
                for (int i = 0; alpha < parent.beta && i < size; i++) {
                    byte[] temp = moves.get(i);
                    Node tempNode = new Node(this, state.newBoard(temp[0], temp[1], temp[2]), temp, counter);
                    tempNode.makeChildrenMin(depth, currentDepth + 1);
                    children.add(tempNode);
                    if (children.get(i).getBeta() > alpha) {
                        alpha = children.get(i).getBeta();
                    }
                }
            }


            // After we've made its children we can mark our current node as having been expanded
            hasBeenExpanded = true;
        }


        // update alpha of parent
        if (alpha < parent.beta) {
            parent.beta = alpha;
        }


        // null the state to save space in tree
        state = null;
        counter.addToVisitedNodeTotal();
    }

    public boolean isLeaf() {
        return children.size() == 0;
    }

    public int getBeta() {
        return beta;
    }

    public int getAlpha() {
        return alpha;
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

    /**
     *
     * @return - the counter list of 3 length byte arrays containing the moves
     */
    public Node_Counter getCounter() {
        return counter;
    }

    public void countAdd1() {
        counter.addToVisitedNodeTotal();
    }
}

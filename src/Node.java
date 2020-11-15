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

    /**
     * Constructor
     *
     * @param parent  - this nodes parent
     * @param state   - the board at this node
     * @param move    - the move we're making
     * @param counter - a node_counter instance to keep track of things
     */
    public Node(Node parent, Board state, byte[] move, Node_Counter counter) {
        this.parent = parent;
        this.state = state;
        this.move = move;
        depth = (byte) (parent.getDepth() + 1);
        children = new ArrayList<>(200);
        hasBeenExpanded = false;

        alpha = Integer.MIN_VALUE;
        beta = Integer.MAX_VALUE;

        this.counter = counter;
    }

    /**
     * Constructor that only needs a state
     *
     * @param state - the state of the board at this node
     */
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
     * Generates the score for the current node using the Minimax algorithm with alpha beta pruning
     */
    public int generateScoreForFullTree(int alphaLocal, int betaLocal) {
        if (isLeaf()) {
            score = state.getScore();
            return score;
        } else {
            // even depth means max node
            if (depth % 2 == 0) {
                int best = Integer.MIN_VALUE;

                for (Node n : children) {
                    int val = n.generateScoreForFullTree(alphaLocal, betaLocal);
                    best = Math.max(best, val);
                    alphaLocal = Math.max(alphaLocal, best);

                    if (betaLocal <= alphaLocal)
                        break;
                }
                score = best;
                return score;
            } else {
                // odd depth means min node
                int best = Integer.MAX_VALUE;

                for (Node n : children) {
                    int val = n.generateScoreForFullTree(alphaLocal, betaLocal);
                    best = Math.min(best, val);
                    betaLocal = Math.min(betaLocal, best);

                    if (betaLocal <= alphaLocal)
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

    /**
     * Generates the children of the root
     * using the minimax algorithm with alpha beta pruning
     *
     * @param depth        - the max depth we want to go to
     * @param currentDepth - the current depth
     */
    public void makeChildrenOfRoot(int depth, int currentDepth) {
        alpha = Integer.MIN_VALUE;

        // Get all the legal moves from the current node
        List<byte[]> moves = getLegalMoves();
        int size = moves.size();
        for (int i = 0; i < size; i++) {
            // temp variable for the move we're on
            byte[] temp = moves.get(i);
            // and a node for that move
            Node tempNode = new Node(this, state.newBoard(temp[0], temp[1], temp[2]), temp, counter);
            // generate its children which are all min nodes
            tempNode.makeChildrenMin(depth, currentDepth + 1);
            children.add(tempNode);
            // and if the beta value of any of our children is higher than alpha, update our alpha
            if (children.get(i).getBeta() > alpha) {
                alpha = children.get(i).getBeta();
            }
        }
        // Then add it to the visited node total
        counter.addToVisitedNodeTotal();
    }

    /**
     * Generates the min node children of the node
     *
     * @param depth        - the max depth
     * @param currentDepth - the current depth
     */
    public void makeChildrenMin(int depth, int currentDepth) {
        // If we're at a leaf node, stop and set the beta to the score at the node
        if (currentDepth == depth) {
            beta = state.getScore();
            counter.updateMaxDepth(currentDepth);
        } else {
            // Get all the legal moves from the current node
            List<byte[]> moves = getLegalMoves();
            int size = moves.size();

            // If there are no legal moves remaining, we're at a terminal node
            if (size == 0) {
                // If we're at a terminal node set beta to the score
                beta = state.getScore();
                counter.updateMaxDepth(currentDepth);
            } else {
                // Loop through the moves we can make from here
                // note that one of our loop conditions is beta > parent.alpha
                // this is when we prune nodes using alpha beta pruning
                for (int i = 0; beta > parent.alpha && i < size; i++) {
                    // temp variables for the move and the node made by it
                    byte[] temp = moves.get(i);
                    Node tempNode = new Node(this, state.newBoard(temp[0], temp[1], temp[2]), temp, counter);

                    // Make the children of this node (which are max nodes)
                    tempNode.makeChildrenMax(depth, currentDepth + 1);
                    children.add(tempNode);

                    // If any of the children's alpha value is lower than our beta, we can lower our beta
                    if (children.get(i).getAlpha() < beta) {
                        beta = children.get(i).getAlpha();
                    }
                }
            }

            // After we've made its children we can mark our current node as having been expanded
            hasBeenExpanded = true;
        }

        // update alpha of parent if needed
        if (beta > parent.alpha) {
            parent.alpha = beta;
        }

        // null the state to save space in tree
        state = null;
        counter.addToVisitedNodeTotal();
    }

    /**
     * Generates the max node children of the node
     *
     * @param depth        - the max depth
     * @param currentDepth - the current depth
     */
    public void makeChildrenMax(int depth, int currentDepth) {
        // If we're at a leaf node, stop and set the beta to the score at the node
        if (currentDepth == depth) {
            alpha = state.getScore();
            counter.updateMaxDepth(currentDepth);
        } else {
            // Get all the legal moves from the current node
            List<byte[]> moves = getLegalMoves();
            int size = moves.size();

            // If there are no legal moves remaining, we're at a terminal node
            if (size == 0) {
                // If we're at a terminal node set alpha to the score
                alpha = state.getScore();
                counter.updateMaxDepth(currentDepth);
            } else {
                // Loop through the moves we can make from here
                // note that one of our loop conditions is alpha < parent.beta
                // this is when we prune nodes using alpha beta pruning
                for (int i = 0; alpha < parent.beta && i < size; i++) {
                    // temp variables for the move and the node made by it
                    byte[] temp = moves.get(i);
                    Node tempNode = new Node(this, state.newBoard(temp[0], temp[1], temp[2]), temp, counter);

                    // Make the children of this node (which are max nodes)
                    tempNode.makeChildrenMin(depth, currentDepth + 1);
                    children.add(tempNode);

                    // If any of the children's beta value is higher than our alpha, we can raise our alpha
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
     * @return - the counter list of 3 length byte arrays containing the moves
     */
    public Node_Counter getCounter() {
        return counter;
    }

    public void countAdd1() {
        counter.addToVisitedNodeTotal();
    }
}

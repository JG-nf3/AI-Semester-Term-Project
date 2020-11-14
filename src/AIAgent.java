public class AIAgent {

    private Game game;
    private Tree tree;


    public AIAgent(Game game) {
        this.game = game;
        System.out.println("AI agent instantiated");

    }

    public void move() {
        //System.out.println("AI turn");
        tree = new Tree(new Node(game.getBoard()));

        // Choose the best move from the given tree
        byte[] chosenMove = tree.getBestMove2();
        //System.out.println(chosenMove[0] + " " + chosenMove[1] + " " + chosenMove[2]);

        // Move the game to the next turn
        // in getBestMove we return a list of byte arrays, which each contain a row, column, and direction
        game.nextTurn(chosenMove[0], chosenMove[1], chosenMove[2]);


    }

    public void setGame(Game game) {
        this.game = game;
    }
}

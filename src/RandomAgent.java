import java.util.Random;

public class RandomAgent {
    private Game game;
    private UI userInterface;

    public RandomAgent(UI ui) {
        this.userInterface = ui;
        // Grab our game from the UI so we know we always have the correct one
        this.game = ui.getGame();
        System.out.println("random agent instantiated");
    }

    public void move() {
        Random rng = new Random();
        // If its not our turn we don't do anything else
        if (game.getBoard().getPlayer1Turn()) return;

        System.out.println("RA turn");
        // Variables to store our randomly generated values
        byte randomRow, randomCol, randomDir;
        // TODO: rewrite this using better stuff see channel at 7:31pm 11/8
        do {
            // Generate 3 random bytes to use for our move
            randomRow = (byte) rng.nextInt(4);
            randomCol = (byte) rng.nextInt(4);
            randomDir = (byte) rng.nextInt(8);
            System.out.println("randomRow: " + randomRow + ", randomCol: " + randomCol + ", randomDir: " + randomDir);
            // If the move is not legal
            // i.e. it would take us out of bounds, or it uses a square with no stones
            // or it uses a square with our opponents stones
        } while (!game.isLegal(randomRow, randomCol, randomDir));

        // Move the game to the next turn
        game.nextTurn(randomRow, randomCol, randomDir);
        // and update the UI appropriately
        userInterface.updateUIForNewTurn();

    }
}

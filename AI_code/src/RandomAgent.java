import java.util.concurrent.ThreadLocalRandom;

public class RandomAgent {
    private Game game;
    private UI userInterface;

    public RandomAgent(UI ui) {
        this.userInterface = ui;
        // Grab our game from the UI so we know we always have the correct one
        this.game = ui.getGame();

    }

    public void move() {
        // If its not our turn we don't do anything else
        if (game.getBoard().getPlayer1Turn()) return;

        // Variables to store our randomly generated values
        byte randomRow, randomCol, randomDir;

        do {
            // Generate 3 random bytes to use for our move
            randomRow = (byte) ThreadLocalRandom.current().nextInt(0, 5);
            randomCol = (byte) ThreadLocalRandom.current().nextInt(0, 5);
            randomDir = (byte) ThreadLocalRandom.current().nextInt(0, 8);
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

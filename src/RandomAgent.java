import java.util.List;
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

        System.out.println("RA turn");
        List<byte[]> legalMoves = game.getLegalMoves();
        if (legalMoves.size() < 1) return;
        System.out.println(legalMoves.size() + " legal moves available for random agent");
        byte[] chosenMove = legalMoves.get(rng.nextInt(legalMoves.size()));
        System.out.println(chosenMove[0] + " " + chosenMove[1] + " " + chosenMove[2]);

        // Move the game to the next turn
        game.nextTurn(chosenMove[0], chosenMove[1], chosenMove[2]);

    }
}

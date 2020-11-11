public class Main {

    public static void main(String[] args) {
        UI conga = new UI();
        int moveCount = 0;

        RandomAgent randomAgent = new RandomAgent(conga.getGame());
        AIAgent aiAgent = new AIAgent(conga.getGame());
        while (true) {
            if (conga.getGame().isOver()) {
                // If the game is over & we're on player 1's turn, then player 2 won on their last move
                System.out.print(conga.getGame().getBoard().getPlayer1Turn() ? "Player 2 won in " : "Player 1 won in ");
                System.out.println(moveCount + " moves");
                break;
            }
            if (conga.getGame().getBoard().getPlayer1Turn()) {
                // Random agent plays as player 1
                randomAgent.move();
            } else {
                // and AI Agent plays as player 2
                aiAgent.move();
            }

            // update the UI & moveCount
            conga.updateUIForNewTurn();
            moveCount++;
            System.out.println("Finished move " + moveCount);
        }
    }
}

public class Main {

    public static void main(String[] args) throws InterruptedException {
        UI conga = new UI();

        RandomAgent randomAgent = new RandomAgent(conga.getGame());
        AIAgent aiAgent = new AIAgent(conga.getGame());

        int player1Wins = 0;
        int totalMovesP1 = 0;
        int totalMovesP2 = 0;
        int stuckCounter = 0;
        long totalAIMoveTime = 0;

        final int gamesToRun = 1;

        for (int i = 0; i < gamesToRun; i++) {
            int moveCount = 0;
            long gameAIMoveTime = 0;

            while (true) {
                if (moveCount > 300) {
                    System.out.println("We got stuck");
                    stuckCounter++;
                    break;
                }
                if (conga.getGame().isOver()) {
                    // If the game is over & we're on player 1's turn, then player 2 won on their last move
                    System.out.print(conga.getGame().getBoard().getPlayer1Turn() ? "Player 2 won in " : "Player 1 won in ");
                    System.out.println(moveCount + " moves");

                    if (!conga.getGame().getBoard().getPlayer1Turn()) player1Wins++;
                    break;
                }
                if (conga.getGame().getBoard().getPlayer1Turn()) {
                    // Random agent plays as player 1
                    randomAgent.move();
                    totalMovesP1++;
                } else {
                    // and AI Agent plays as player 2
                    long start = System.currentTimeMillis();
                    aiAgent.move();
                    long end = System.currentTimeMillis();
                    // uncomment code if you want to print out time per move
                    // System.out.println("AI made a move in " + (end - start) + " milliseconds.");
                    // System.out.println();
                    gameAIMoveTime += (end - start);
                    totalMovesP2++;
                }

                // update the UI & moveCount
                conga.updateUIForNewTurn();
                moveCount++;

                // Create a delay between moves so that the user can see the moves happening
                Thread.sleep(200);
            }

            System.out.println("Total time was " + gameAIMoveTime + " milliseconds.");
            aiAgent.printTotals();

            totalAIMoveTime += gameAIMoveTime;

            // Don't restart game for our packaged JAR file, we'll only run 1 game
            // uncomment code if you want to play more than 1 game
            //conga.restartGame();
            //randomAgent.setGame(conga.getGame());
            //aiAgent.setGame(conga.getGame());
        }

        System.out.println("Player 1 won " + player1Wins + " games out of " + gamesToRun);
        System.out.println("Player 2 won " + (gamesToRun - player1Wins - stuckCounter) + " games out of " + gamesToRun);
        System.out.println("Player 1 made " + totalMovesP1 + " moves over " + gamesToRun + " games");
        System.out.println("Player 2 made " + totalMovesP2 + " moves over " + gamesToRun + " games");
        System.out.println("Player 1 average moves = " + (totalMovesP1 / gamesToRun));
        System.out.println("Player 2 average moves = " + (totalMovesP2 / gamesToRun));
        System.out.println("Total moves made = " + (totalMovesP1 + totalMovesP2));
        System.out.println("The AI Agent got stuck " + stuckCounter + " times over " + gamesToRun + " games");
        System.out.println("The AI took a total of " + totalAIMoveTime + " milliseconds, or " + totalAIMoveTime / 1000 + " seconds for its moves.");
        System.out.println("This is an average of " + totalAIMoveTime / totalMovesP2 + " milliseconds per move.");
    }
}
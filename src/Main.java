public class Main {

    public static void main(String[] args) {
        UI conga = new UI();
        int moveCount = 0;

        RandomAgent randomAgent = new RandomAgent(conga.getGame());
        AIAgent aiAgent = new AIAgent(conga.getGame());
        while(true) {
            if (conga.getGame().isOver()) {
                System.out.println(moveCount);
                return;
            }
            if (! conga.getGame().getBoard().getPlayer1Turn()) {
                aiAgent.move();
                conga.updateUIForNewTurn();
            } else {
                randomAgent.move();
                conga.updateUIForNewTurn();
            }
            moveCount++;
        }

        /**
        RandomAgent randomAgent = new RandomAgent(conga.getGame());
        while(true) {
            if (conga.getGame().isOver()) return;
            if (conga.getGame().getBoard().getPlayer1Turn()) {
                // Do nothing for now, eventually minimax agent will play here
            } else {
                randomAgent.move();
                conga.updateUIForNewTurn();
            }
        }
         */

        /**
        AIAgent aiAgent = new AIAgent(conga.getGame());
        while(true) {
            if (conga.getGame().isOver()) {
                System.out.println(aiAgent.getMovesCount());
                return;
            }
            if (conga.getGame().getBoard().getPlayer1Turn()) {
                // Do nothing for now, eventually minimax agent will play here
            } else {
                aiAgent.move();
                conga.updateUIForNewTurn();
            }
        }
         */

    }
}

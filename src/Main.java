public class Main {

    public static void main(String[] args) {
        UI conga = new UI();
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
    }
}

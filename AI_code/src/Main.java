public class Main {

    public static void main(String[] args) {
        UI conga = new UI();
        RandomAgent randomAgent = new RandomAgent(conga);
        // TODO: figure out how to make the randomAgent actually make moves while the UI thread is running
        randomAgent.move();
    }
}

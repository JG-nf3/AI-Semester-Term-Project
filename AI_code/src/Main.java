public class Main {

    public static void main(String[] args) {
        UI conga = new UI();
        RandomAgent randomAgent = new RandomAgent(conga);
        while(true) {
            randomAgent.move();
        }
    }
}

import base.WordleFactory;
import view.UserInterface;

public class Main {
    public static void main(String[] args) {
        WordleFactory wordleFactory = new WordleFactory();
        UserInterface userInterface = wordleFactory.createApp();
        userInterface.runGame();
    }
}

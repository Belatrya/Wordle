import base.WordleFactory;
import model.exceptions.DictionaryIsNotFoundException;
import view.UserInterface;

public class Main {
    public static void main(String[] args) {
        WordleFactory wordleFactory = new WordleFactory();
        UserInterface userInterface = wordleFactory.createApp();
        try {
            userInterface.runGame();
        } catch (DictionaryIsNotFoundException e) {
            userInterface.talkWithUser(e.getMessage());
        }
    }
}

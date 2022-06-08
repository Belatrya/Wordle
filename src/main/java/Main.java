import base.Game;
import model.exceptions.DictionaryIsNotFoundException;
import view.UserInterface;

public class Main {
    public static void main(String[] args) {
        try {
            Game game = new Game();
            game.startGame();
            game.playGame();
        } catch (DictionaryIsNotFoundException e) {
            UserInterface userInterface = new UserInterface();
            userInterface.talkWithUser(e.getMessage());
        }
    }
}

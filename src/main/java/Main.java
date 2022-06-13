import base.Game;
import model.exceptions.DictionaryIsNotFoundException;
import view.UserInterface;

public class Main {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        try {
            Game game = new Game();
            userInterface.greetingUserAndRules(game.getGameRuleCountOfRounds());
            game.playGame();
        } catch (DictionaryIsNotFoundException e) {
            userInterface.talkWithUser(e.getMessage());
        }
    }
}

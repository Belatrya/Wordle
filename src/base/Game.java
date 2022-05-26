package base;

import io.UserInterface;

public class Game {

    public void startGame() {
        UserInterface.greetingUser();
    }

    public void playRound() {
        String userWord = getUserWord();
    }

    private String getUserWord() {
        return UserInterface.getUserWord();
    }

}

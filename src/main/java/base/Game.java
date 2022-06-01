package base;

import view.UserInterface;

/**
 * Represents the game process.
 */
public class Game {
    private final static int RULE_GAME_ROUNDS = 6;
    private final static int RULE_LETTERS_COUNT = 5;

    private UserInterface userInterface;
    public Game() {
        userInterface = new UserInterface();
    }

    /**
     * Starts the game with greeting the user.
     */
    public void startGame() {
        userInterface.greetingUser();
    }

    /**
     * Represents a single game round as user's one try to guess the hidden word.
     */
    public void playRound() {
        String userWord = getUserWord();
    }
    private String getUserWord() {
        return userInterface.getUserWord();
    }
}
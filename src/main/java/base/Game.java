package main.java.base;

import main.java.view.UserInterface;

public class Game {
    private final static int RULE_GAME_ROUNDS = 5;
    private final static int RULE_LETTERS_COUNT = 5;
    private UserInterface userInterface;
    public Game() {
        userInterface = new UserInterface();
    }
    public void startGame() {
        userInterface.greetingUser();
    }
    public void playRound() {
        String userWord = getUserWord();
    }
    private String getUserWord() {
        return userInterface.getUserWord();
    }
}
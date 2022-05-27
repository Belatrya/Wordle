package main.java.base;

import main.java.userinterface.UserInterface;

public class Game {
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
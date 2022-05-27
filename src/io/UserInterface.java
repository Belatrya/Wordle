package io;
import java.util.Scanner;
public class UserInterface {
    private static final String GREETING_USER = "Welcome to the game Wordle!";
    private static final String ASK_TO_TYPE_WORD = "Please type a word and tap enter";
    public void greetingUser() {
        talkWithUser(GREETING_USER);
    }
    private void talkWithUser(String phrase) {
        System.out.println(phrase);
    }
    public String getUserWord() {
        String userWord;
        talkWithUser(ASK_TO_TYPE_WORD);

        try (Scanner scanner = new Scanner(System.in)) {
            userWord = scanner.nextLine();
        }
        return userWord;
    }
}
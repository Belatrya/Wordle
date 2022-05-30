package main.java.view;
import java.util.Scanner;

/**
 * Represents a dialog with the user.
 */
public class UserInterface {
    private static final String GREETING_USER = "Welcome to the game Wordle!";
    private static final String ASK_TO_TYPE_WORD = "Please type a word and tap enter";

    /**
     * Greets the user.
     */
    public void greetingUser() {
        talkWithUser(GREETING_USER);
    }
    private void talkWithUser(String phrase) {
        System.out.println(phrase);
    }

    /**
     * Asks the user to type a word and returns it.
     */
    public String getUserWord() {
        String userWord;
        talkWithUser(ASK_TO_TYPE_WORD);

        try (Scanner scanner = new Scanner(System.in)) {
            userWord = scanner.nextLine();
        }
        return userWord;
    }
}
package io;
import java.util.Scanner;

public class UserInterface {

    private static final String GREETING_USER = "Welcome to the game Wordle!";
    private static final String ASK_TO_TYPE_WORD = "Please type a word and tap enter";

    public static void greetingUser() {
        talkWithUser(GREETING_USER);
    }

    private static void askForWord() {
        talkWithUser(ASK_TO_TYPE_WORD);
    }

    private static void talkWithUser(String phrase) {
        System.out.println(phrase);
    }

    public static String getUserWord() {
        String userWord = "";
        askForWord();

        try (Scanner scanner = new Scanner(System.in)) {
            userWord = scanner.nextLine();
        }
        return userWord;
    }
}

package view;

import java.io.Console;
import java.util.Scanner;

/**
 * Represents a dialog with the user.
 */
public class UserInterface {
    private static final String GREETING_USER = "Welcome to the game Wordle!";
    private static final String ASK_TO_TYPE_WORD = "Please type a word and tap enter";
    private static final String WORD_NOT_EXIST = "\"%s\" word doesn't exist!";
    private static final String WRONG_WORD = "\"%s\" word is wrong.";
    private static final String CORRECT_WORD = "\"%s\" word is correct!";
    private static final String LETTER_ON_THE_RIGHT_PLACE = "%s - letter exists and is on the right place!";
    private static final String LETTER_NOT_ON_THE_RIGHT_PLACE = "%s - letter exists but not not on the right place.";
    private static final String LETTER_NOT_EXIST = "%s - letter doesn't exist in the hidden word.";

    /**
     * Greets the user.
     */
    public void greetingUser() {
        talkWithUser(GREETING_USER);
    }

    /**
     * Writes the phrase about user's word according to the 'correct' parameter.
     *
     * @param correct  parameter to say for the current word, true for correct and false otherwise.
     * @param userWord user's word.
     */
    public void sayIsUsersWordCorrect(boolean correct, String userWord) {
        if (correct) {
            talkWithUser(String.format(CORRECT_WORD, userWord));
        } else {
            talkWithUser(String.format(WRONG_WORD, userWord));
        }
    }

    /**
     * Writes the phrase about user's word letter according to the 'rightPlace' parameter.
     *
     * @param rightPlace true if the letter from the user's word is in the same place for the hidden word.
     * @param letter     letter from the user's word.
     */
    public void writeLetterOnTheRightPlace(boolean rightPlace, char letter) {
        if (rightPlace) {
            talkWithUser(String.format(LETTER_ON_THE_RIGHT_PLACE, letter));
        } else {
            talkWithUser(String.format(LETTER_NOT_ON_THE_RIGHT_PLACE, letter));
        }
    }

    /**
     * Writes phrase that letter doesn't exist in the hidden word.
     *
     * @param letter letter from the user's word.
     */
    public void writeLetterNotExistInHiddenWord(char letter) {
        talkWithUser(String.format(LETTER_NOT_EXIST, letter));
    }

    /**
     * Writes the phrase about user's word does not exist.
     *
     * @param userWord user's word.
     */
    public void sayUsersWordNotExist(String userWord) {
        talkWithUser(String.format(WORD_NOT_EXIST, userWord));
    }

    /**
     * Writes the phrase for the user into the console.
     *
     * @param phrase to write into the console.
     */
    public void talkWithUser(String phrase) {
        System.out.println(phrase);
    }

    /**
     * Asks the user to type a word and returns it.
     */
    public String getUserWord() {
        talkWithUser(ASK_TO_TYPE_WORD);
        return getConsoleInput();
    }

    private String getConsoleInput() {
        Console console = System.console();

        if (console == null) {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine();
        }
        return console.readLine();
    }
}
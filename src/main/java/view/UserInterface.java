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
    private static final String LETTER_CORRECT = "%s - CORRECT";
    private static final String LETTER_NOT_REALLY = "%s - NOT REALLY";
    private static final String LETTER_WRONG = "%s - WRONG";
    private static final String ROUND_STARTED = "The round #%d has started!";
    private static final String WINNER = "Congratulations you are the winner!";
    private static final String LOSER = "Sorry you lost the game.";
    private static final String HIDDEN_WORD = "The hidden word was \"%s\".";
    private static final String RULES_LETTERS_ROUNDS =
            "There is the hidden word from 5 letters, you will have %d rounds to guess it.";
    private static final String RULES_GUESSING = "Every round you will need to type 1 existing word.";
    private static final String RULES_LETTERS_DESCRIBING =
            "If your word doesn't equal to the hidden, there will be description for every letter:";
    private static final String RULES_LETTER_NOT_EXIST = " - WRONG - letter doesn't exist in the hidden word";
    private static final String RULES_LETTER_NOT_ON_THE_RIGHT_PLACE =
            " - NOT REALLY - letter exists but not on the right place";
    private static final String RULES_LETTER_ON_THE_RIGHT_PLACE  =
            " - CORRECT - letter exists and is on the right place";
    private static final String GOOD_LUCK = "Let's start and good luck!";



    /**
     * Greets the user.
     */
    public void greetingUserAndRules(int rounds) {
        talkWithUser(GREETING_USER);
        talkWithUser(String.format(RULES_LETTERS_ROUNDS, rounds));
        talkWithUser(RULES_GUESSING);
        talkWithUser(RULES_LETTERS_DESCRIBING);
        talkWithUser(RULES_LETTER_NOT_EXIST);
        talkWithUser(RULES_LETTER_NOT_ON_THE_RIGHT_PLACE);
        talkWithUser(RULES_LETTER_ON_THE_RIGHT_PLACE);
        talkWithUser(GOOD_LUCK);
    }

    /**
     * Writes the phrase about the number of started round.
     *
     * @param number number of the round.
     */
    public void writeNumberOfRounds(int number) {
        talkWithUser(String.format(ROUND_STARTED, number));
    }

    /**
     * Writes the phrase about the game result. If the user lost the game it writes the hidden word.
     *
     * @param result     true if the user has won, false otherwise.
     * @param hiddenWord hidden word.
     */
    public void writeGameResult(boolean result, String hiddenWord) {
        if (result) {
            talkWithUser(WINNER);
        } else {
            talkWithUser(LOSER);
            talkWithUser(String.format(HIDDEN_WORD, hiddenWord));
        }
    }

    /**
     * Writes the phrase about user's word according to the 'correct' parameter.
     *
     * @param correct  parameter to say for the current word, true for correct and false otherwise.
     * @param userWord user's word.
     */
    public void writeIsUsersWordCorrect(boolean correct, String userWord) {
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
            talkWithUser(String.format(LETTER_CORRECT, letter));
        } else {
            talkWithUser(String.format(LETTER_NOT_REALLY, letter));
        }
    }

    /**
     * Writes phrase that letter doesn't exist in the hidden word.
     *
     * @param letter letter from the user's word.
     */
    public void writeLetterNotExistInHiddenWord(char letter) {
        talkWithUser(String.format(LETTER_WRONG, letter));
    }

    /**
     * Writes the phrase about user's word does not exist in the dictionary.
     *
     * @param userWord user's word.
     */
    public void writeUsersWordNotExist(String userWord) {
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
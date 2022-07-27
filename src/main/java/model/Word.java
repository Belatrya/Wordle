package model;

import base.Checker;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Represents a word from one user's try to guess the hidden. It consists of letters.
 */
@Data
@Component
@RequiredArgsConstructor
@Scope("prototype")
public class Word {
    private String value;
    @NonNull
    private List<Letter> letters;
    @NonNull
    private Checker checker;

    public void setValue(String value) {
        this.value = value.toUpperCase();
    }

    /**
     * Sets up parameters for the user word's letters in comparing of the hidden word's letters.
     *
     * @param hiddenWord a string value of the hidden word.
     */
    public void setComparingFlagForLetters(String hiddenWord) {
        for (int i = 0; i < value.length(); i++) {
            char[] userWordChars = value.toCharArray();
            Letter letter = new Letter(userWordChars[i]);

            if (checker.isLetterExistInTheHiddenWord(letter.value, hiddenWord)) {
                if (checker.isLetterOnTheRightPlace(i, hiddenWord, value)) {
                    letter.setCorrect(true);
                } else {
                    letter.setNotOnTheRightPlace(true);
                }
            }
            getLetters().add(letter);
        }
    }

    /**
     * The class to store letters with parameters which depend on the hidden word letters.
     */
    @Data
    @RequiredArgsConstructor
    static class Letter {
        @NonNull
        private char value;
        private boolean correct;
        private boolean notOnTheRightPlace;
    }
}

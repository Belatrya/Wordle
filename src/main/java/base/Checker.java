package base;

import model.Dictionary;

/**
 * Represents checks for words.
 */
public class Checker {
    private Dictionary allWordDictionary;

    public Checker(Dictionary allWordDictionary) {
        this.allWordDictionary = allWordDictionary;
    }

    /**
     * Compares user's word with the hidden word.
     *
     * @return true if the user's word equals to the hidden word, false otherwise.
     */
    public boolean isHiddenEqualsToUserWord(String hiddenWord, String userWord) {
        if (hiddenWord.equalsIgnoreCase(userWord)) {
            return true;
        }
        return false;
    }

    /**
     * True if a word exists in the dictionary.
     *
     * @return true if a word exists in the dictionary, false otherwise.
     */
    public boolean isUserWordExists(String userWord) {
        return allWordDictionary.isExists(userWord);
    }

    /**
     * Returns true if the letter from the user's word is in the same place for the hidden word.
     *
     * @param letterIndex index for the checking letters.
     * @return true if the letter from the user's word is in the same place for the hidden word, false otherwise.
     */
    public boolean isLetterOnTheRightPlace(int letterIndex, String hiddenWord, String userWord) {
        char userWordLetter = userWord.toUpperCase().charAt(letterIndex);
        char hiddenWordLetter = hiddenWord.toUpperCase().charAt(letterIndex);
        return userWordLetter == hiddenWordLetter;
    }

    /**
     * Returns true if the letter from the user's word exists in the hidden word (maybe on the other place).
     *
     * @param letter letter from the user's word.
     * @return true if the letter from the user's word exists in the hidden word, false otherwise.
     */
    public boolean isLetterExistInTheHiddenWord(char letter, String hiddenWord) {
        return hiddenWord.contains(String.valueOf(letter).toUpperCase());
    }
}
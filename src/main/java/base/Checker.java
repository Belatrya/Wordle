package base;

import model.Dictionary;
import model.DictionaryFileStorage;
import model.DictionaryType;

/**
 * Represents checks for words.
 */
public class Checker {
    private String hiddenWord;
    private String userWord;

    public Checker(String hiddenWord, String userWord) {
        this.hiddenWord = hiddenWord;
        this.userWord = userWord;
    }

    public Checker(String userWord) {
        this.userWord = userWord;
    }

    /**
     * Compares user's word with the hidden word.
     *
     * @return true if the user's word equals to the hidden word, false otherwise.
     */
    public boolean areWordsEqual() {
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
    public boolean isWordExists() {
        Dictionary dictionary = new DictionaryFileStorage(DictionaryType.ALL_WORDS);
        return dictionary.isExists(userWord);
    }

    /**
     * Returns true if the letter from the user's word is in the same place for the hidden word.
     *
     * @param letterIndex index for the checking letters.
     * @return true if the letter from the user's word is in the same place for the hidden word, false otherwise.
     */
    public boolean isLetterOnTheRightPlace(int letterIndex) {
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
    public boolean isLetterExistInTheHiddenWord(char letter) {
        return hiddenWord.contains(String.valueOf(letter).toUpperCase());
    }
}
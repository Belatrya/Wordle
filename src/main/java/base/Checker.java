package base;

import model.Dictionary;
import model.DictionaryFileStorage;

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
     * True if the user's word equals to the hidden word.
     *
     * @return true if the user's word equals to the hidden word, false otherwise.
     */
    public boolean areWordsEqual() {
        if (!hiddenWord.equalsIgnoreCase(userWord)) {
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
        Dictionary dictionary = new DictionaryFileStorage();
        return dictionary.isExists(userWord);
    }
}
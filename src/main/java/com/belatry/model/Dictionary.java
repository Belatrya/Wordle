package com.belatry.model;

import java.util.List;
import java.util.Optional;

/**
 * Represents methods to access to the dictionary from different types of sources.
 */
public interface Dictionary {
    /**
     * Returns a count of all words in the dictionary.
     *
     * @return count of words.
     */
    int getWordsCount();

    /**
     * Returns an Optional instance for a word from the dictionary by the line number.
     *
     * @param lineNumber the number of a line where need to find the word in the dictionary.
     * @return an Optional instance for a word from the dictionary.
     */
    Optional<String> getWord(int lineNumber);

    /**
     * Returns true if the dictionary contains the parameter word with ignoring case.
     *
     * @param word which need to search in the dictionary.
     * @return true if the dictionary contains the parameter word, false otherwise.
     */
    boolean isExists(String word);

    /**
     * Returns all words in the dictionary.
     *
     * @return all words in the dictionary.
     */
    List<String> getAllWords();

    /**
     * Adds the word in the dictionary.
     *
     * @param word to add.
     */
    void add(String word);

    /**
     * Deletes the word from the dictionary.
     *
     * @param word to delete.
     */
    void delete(String word);
}
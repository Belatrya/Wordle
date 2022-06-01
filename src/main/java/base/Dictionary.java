package base;

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
     * Returns a word from the dictionary by the line number.
     *
     * @param lineNumber the number of a line where need to find the word in the dictionary.
     * @return a word from the dictionary.
     */
    String getWord(int lineNumber);

    /**
     * Returns true if the dictionary contains the parameter word with ignoring case.
     *
     * @param word which need to search in the dictionary.
     * @return true if the dictionary contains the parameter word, false otherwise.
     */
    boolean isExists(String word);
}
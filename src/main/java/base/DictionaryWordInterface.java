package base;

/**
 * Represents methods to access to the dictionary from different types of sources.
 */
public interface DictionaryWordInterface {
    int getWordsCount();
    String getWord(int lineNumber);
    boolean isExists(String word);
}
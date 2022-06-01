package base;

import base.exceptions.DictionaryIsNotFoundException;

/**
 * Represents methods to access to the dictionary from different types of sources.
 */
public interface DictionaryWordInterface {
    int getWordsCount();
    String getWord(int lineNumber) throws DictionaryIsNotFoundException;
    boolean isExists(String word) throws DictionaryIsNotFoundException;
}
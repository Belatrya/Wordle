package base;

import base.exceptions.DictionaryIsNotFoundException;
import base.exceptions.EmptyDictionaryFileException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Represents a storage to get access to the dictionary file.
 */
public class DictionaryStorage implements DictionaryWordInterface {
    private static final String DICTIONARY_PATH = ".\\out\\resources\\russian5letterwords.txt";

    private Path dictionaryPath;
    private int wordsCount;

    public DictionaryStorage() throws EmptyDictionaryFileException, DictionaryIsNotFoundException {
        this.dictionaryPath = Path.of(DICTIONARY_PATH);

        try {
            int wordsCount = countWords();
            if (wordsCount != 0) {
                this.wordsCount = wordsCount;
            } else {
                throw new EmptyDictionaryFileException();
            }
        } catch (IOException e) {
            throw new DictionaryIsNotFoundException();
        }
    }

    /**
     * Returns a count of all words in the dictionary.
     * @return count of words.
     */
    @Override
    public int getWordsCount() {
        return wordsCount;
    }

    private int countWords() throws IOException {
        int count = 0;

        try (Scanner scanner = new Scanner(dictionaryPath, StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine()) {
                count++;
                scanner.nextLine();
            }

            return count;
        }
    }

    /**
     * Returns a word from the dictionary by the line number.
     * @param lineNumber the number of a line where need to find the word in the dictionary.
     * @return a word from the dictionary.
     * @throws DictionaryIsNotFoundException in case the issue with access to the dictionary.
     */
    @Override
    public String getWord(int lineNumber) throws DictionaryIsNotFoundException {
        String foundWord = "";
        try(Scanner scanner = new Scanner(dictionaryPath, StandardCharsets.UTF_8)) {
            for (int i = 1; i < lineNumber; i++) {
                scanner.nextLine();
            }
            foundWord = scanner.nextLine();
        } catch (IOException e) {
            throw new DictionaryIsNotFoundException();
        }

        return foundWord;
    }

    /**
     * Returns true if the dictionary contains the parameter word with ignoring case.
     * @param word which need to search in the dictionary.
     * @return true if the dictionary contains the parameter word, false otherwise.
     * @throws DictionaryIsNotFoundException in case the issue with access to the dictionary.
     */
    @Override
    public boolean isExists(String word) throws DictionaryIsNotFoundException {
        boolean isWordFound = false;
        try(Scanner scanner = new Scanner(dictionaryPath, StandardCharsets.UTF_8)) {
            while((!isWordFound) && (scanner.hasNext())) {
                if (scanner.nextLine().equalsIgnoreCase(word)) {
                    isWordFound = true;
                }
            }
        } catch (IOException e) {
            throw new DictionaryIsNotFoundException();
        }

        return isWordFound;
    }
}

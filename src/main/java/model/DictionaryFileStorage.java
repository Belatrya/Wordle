package model;

import model.exceptions.DictionaryIsNotFoundException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Scanner;

/**
 * Represents a storage to get access to the dictionary file.
 * Throws DictionaryIsNotFoundException in case any issues with the dictionary.
 */
public class DictionaryFileStorage implements Dictionary {
    private Path dictionaryPath;
    private int wordsCount;
    private static final String EMPTY_FILE_EXCEPTION = "The dictionary file is empty!";

    public DictionaryFileStorage(DictionaryType type) throws DictionaryIsNotFoundException {
        dictionaryPath = Path.of(type.getPath());

        int wordsCount = countWords();
        if (wordsCount != 0) {
            this.wordsCount = wordsCount;
        } else {
            throw new DictionaryIsNotFoundException(EMPTY_FILE_EXCEPTION);
        }
    }

    /**
     * Returns a count of all words in the dictionary.
     *
     * @return count of words.
     */
    @Override
    public int getWordsCount() {
        return wordsCount;
    }

    private int countWords() {
        int count = 0;

        try (Scanner scanner = new Scanner(dictionaryPath, StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine()) {
                count++;
                scanner.nextLine();
            }

            return count;
        } catch (IOException e) {
            throw new DictionaryIsNotFoundException();
        }
    }

    /**
     * Returns an Optional instance for a word from the dictionary by the line number.
     *
     * @param lineNumber the number of a line where need to find the word in the dictionary.
     * @return an Optional instance for a word from the dictionary.
     */
    @Override
    public Optional<String> getWord(int lineNumber) {
        if ((lineNumber <= wordsCount) && (lineNumber > 0)) {

            try (Scanner scanner = new Scanner(dictionaryPath, StandardCharsets.UTF_8)) {
                for (int i = 1; i < lineNumber; i++) {
                    scanner.nextLine();
                }
                return Optional.of(scanner.nextLine());
            } catch (IOException e) {
                throw new DictionaryIsNotFoundException();
            }
        }
        return Optional.empty();
    }

    /**
     * Returns true if the dictionary contains the parameter word with ignoring case.
     *
     * @param word which need to search in the dictionary.
     * @return true if the dictionary contains the parameter word, false otherwise.
     */
    @Override
    public boolean isExists(String word) {
        try (Scanner scanner = new Scanner(dictionaryPath, StandardCharsets.UTF_8)) {
            while (scanner.hasNext()) {
                if (scanner.nextLine().equalsIgnoreCase(word)) {
                    return true;
                }
            }
        } catch (IOException e) {
            throw new DictionaryIsNotFoundException();
        }

        return false;
    }
}

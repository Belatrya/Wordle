package com.belatry.model;

import com.belatry.model.exceptions.DictionaryIsNotFoundException;
import lombok.Getter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Represents a storage to get access to the dictionary file.
 * Throws DictionaryIsNotFoundException in case any issues with the dictionary.
 */
public class DictionaryFileStorage implements Dictionary {
    @Getter
    private Path dictionaryPath;
    @Getter
    private int wordsCount;
    private static final String EMPTY_FILE_EXCEPTION = "The dictionary file is empty!";

    public DictionaryFileStorage(String path) throws DictionaryIsNotFoundException {
        dictionaryPath = Path.of(path);

        int wordsCount = countWords();
        if (wordsCount != 0) {
            this.wordsCount = wordsCount;
        } else {
            throw new DictionaryIsNotFoundException(EMPTY_FILE_EXCEPTION);
        }
    }

    private int countWords() {
        int count = 0;

        try (Scanner scanner = new Scanner(getDictionaryPath(), StandardCharsets.UTF_8)) {
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

            try (Scanner scanner = new Scanner(getDictionaryPath(), StandardCharsets.UTF_8)) {
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
        try (Scanner scanner = new Scanner(getDictionaryPath(), StandardCharsets.UTF_8)) {
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

    /**
     * Returns all words in the dictionary.
     *
     * @return all words in the dictionary.
     */
    @Override
    public List<String> getAllWords() {
        List<String> allWords = new ArrayList<>(getWordsCount());
        for (int i = 1; i <= getWordsCount(); i++) {
            allWords.add(getWord(i).get());
        }
        return allWords;
    }

    /**
     * Adds the word to the end of the file if the word doesn't exist yet.
     *
     * @param word to add.
     */
    @Override
    public void add(String word) {
        if ((!word.isEmpty()) && (!isExists(word))) {
            try (Writer writer = new FileWriter(getDictionaryPath().toAbsolutePath().toString(), StandardCharsets.UTF_8,
                    true)) {
                writer.append(word.toUpperCase());
                writer.append(System.lineSeparator());
            } catch (IOException e) {
                throw new DictionaryIsNotFoundException();
            }
        }
    }

    /**
     * Deletes the word from the dictionary if it exists.
     *
     * @param word to delete.
     */
    @Override
    public void delete(String word) {
        String tempFileName = String.format("%s\\TMP%s", getDictionaryPath().getParent(),
                getDictionaryPath().getFileName());
        Path tempFilePath = Path.of(tempFileName);
        try (Scanner scanner = new Scanner(getDictionaryPath(), StandardCharsets.UTF_8);
             Writer writer = new FileWriter(tempFileName, StandardCharsets.UTF_8)) {
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (!word.equalsIgnoreCase(line)) {
                    writer.write(line.toUpperCase());
                    writer.write(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            throw new DictionaryIsNotFoundException();
        }
        try {
            Files.move(tempFilePath, getDictionaryPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new DictionaryIsNotFoundException();
        }
    }
}

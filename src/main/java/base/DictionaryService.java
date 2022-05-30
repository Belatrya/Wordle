package main.java.base;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class DictionaryService {
    private static final String DICTIONARY_PATH = "src\\main\\java\\base\\dictionary\\russian5letterwords.txt";

    private Path dictionaryPath;
    private int wordsCount;

    public DictionaryService() throws IOException {
        this.dictionaryPath = Path.of(DICTIONARY_PATH);

        int wordsCount = getWordsCount();
        if (wordsCount != 0) {
            this.wordsCount = wordsCount;
        } else {
            throw new IOException("The dictionary file is empty!");
        }
    }

    private int getWordsCount() throws IOException {
        int count = 0;

        try (Scanner scanner = new Scanner(dictionaryPath)) {
            while (scanner.hasNextLine()) {
                count++;
                scanner.nextLine();
            }

            return count;
        }
    }

    public String getRandomExistingWord() {
        String randomExistingWord = "";

        int range = (wordsCount - 1) + 1;
        int randomIndex = (int)(Math.random() * range) + 1;

        try(Scanner scanner = new Scanner(dictionaryPath)) {
            for (int i = 1; i < randomIndex; i++) {
                scanner.nextLine();
            }
            randomExistingWord = scanner.nextLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return randomExistingWord;
    }

    public boolean containsWord(String word) {

        return true;
    }
}

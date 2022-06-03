import base.Game;
import model.Dictionary;
import model.DictionaryFileStorage;
import model.exceptions.DictionaryIsNotFoundException;
import view.UserInterface;

import java.util.Optional;

public class Main {
    private static final String THERE_IS_NO_WORD_MESSAGE = "there is no word on %s line \n";
    private static final String HIDDEN_WORD_ON_LINE_MESSAGE = "hidden word from %s line: %s \n";
    private static final String WORDS_COUNT_MESSAGE = "words count: %d \n";
    private static final String IS_THIS_WORD_EXIST_MESSAGE = "is this word exists: %s \n";

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();

        // block for feature test, will be removed in the next feature
        try {
            Dictionary dictionaryFileStorage = new DictionaryFileStorage();

            int wordsCount = dictionaryFileStorage.getWordsCount();
            System.out.printf(WORDS_COUNT_MESSAGE, wordsCount);

            int[] testLines = new int[]{357, 0, 1, wordsCount, wordsCount + 1};
            for (int line : testLines) {
                checkWordPresentsTest(dictionaryFileStorage, line);
            }

            UserInterface userInterface = new UserInterface();
            String userWord = userInterface.getUserWord();
            // bug: букву 'ё' надо приравнять к 'е' при сравнении или указать, что они не равны в начале игры
            System.out.printf(IS_THIS_WORD_EXIST_MESSAGE, dictionaryFileStorage.isExists(userWord));
        } catch (DictionaryIsNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void checkWordPresentsTest(Dictionary dictionary, int line) {
        Optional<String> word = dictionary.getWord(line);

        if (word.isPresent()) {
            System.out.printf(HIDDEN_WORD_ON_LINE_MESSAGE, line, word.get());
        } else {
            System.out.printf(THERE_IS_NO_WORD_MESSAGE, line);
        }
    }
}

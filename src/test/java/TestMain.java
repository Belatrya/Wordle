import base.Game;
import model.Dictionary;
import model.DictionaryFileStorage;
import model.exceptions.DictionaryIsNotFoundException;
import view.UserInterface;

import java.lang.reflect.Field;
import java.util.Optional;

public class TestMain {
    private static final String THERE_IS_NO_WORD_MESSAGE = "there is no word on %s line \n";
    private static final String HIDDEN_WORD_ON_LINE_MESSAGE = "hidden word from %s line: %s \n";
    private static final String WORDS_COUNT_MESSAGE = "words count: %d \n";

    public static void main(String[] args) {
        try {
            Dictionary dictionaryFileStorage = new DictionaryFileStorage();

            int wordsCount = dictionaryFileStorage.getWordsCount();
            System.out.printf(WORDS_COUNT_MESSAGE, wordsCount);

            int[] testLines = new int[]{357, 0, 1, wordsCount, wordsCount + 1};
            for (int line : testLines) {
                checkWordPresentsTest(dictionaryFileStorage, line);
            }

            Game game = new Game();
            game.startGame();

            Field hiddenWordField = game.getClass().getDeclaredField("hiddenWord");
            hiddenWordField.setAccessible(true);
            String hiddenWord = (String) hiddenWordField.get(game);

            System.out.println(hiddenWord);

            game.playRound();

        } catch (DictionaryIsNotFoundException e) {
            UserInterface userInterface = new UserInterface();
            userInterface.talkWithUser(e.getMessage());
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
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
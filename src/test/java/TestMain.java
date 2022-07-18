import base.ConfigurationContext;
import base.Game;
import model.Dictionary;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.UserInterface;

import java.util.Optional;

public class TestMain {
    private static final String THERE_IS_NO_WORD_MESSAGE = "there is no word on %s line \n";
    private static final String HIDDEN_WORD_ON_LINE_MESSAGE = "hidden word from %s line: %s \n";
    private static final String WORDS_COUNT_MESSAGE = "words count: %d \n";

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationContext.class);
        UserInterface userInterface = context.getBean(UserInterface.class);

        Dictionary dictionaryFileStorage = (Dictionary) context.getBean("allWordsDictionary");

        int wordsCount = dictionaryFileStorage.getWordsCount();
        System.out.printf(WORDS_COUNT_MESSAGE, wordsCount);

        int[] testLines = new int[]{357, 0, 1, wordsCount, wordsCount + 1};
        for (int line : testLines) {
            checkWordPresentsTest(dictionaryFileStorage, line);
        }

        Game game = context.getBean(Game.class);
        System.out.println(game.getHiddenWord());

        userInterface.runGame();
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
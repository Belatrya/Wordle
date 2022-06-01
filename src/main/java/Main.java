import model.Dictionary;
import model.DictionaryFileStorage;
import base.Game;
import model.exceptions.DictionaryIsNotFoundException;
import view.UserInterface;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();

        // block for feature test, will be removed in the next feature
        try {
            Dictionary dictionaryFileStorage = new DictionaryFileStorage();

            System.out.println("words count: " + dictionaryFileStorage.getWordsCount());
            int line;
            line = 357;
            if (dictionaryFileStorage.getWord(line).isPresent()) {
                System.out.println("hidden word from " + line + " line: " + dictionaryFileStorage.getWord(line).get());
            } else {
                System.out.println("there is no word on " + line + " line");
            }
            line = 3483;
            if (dictionaryFileStorage.getWord(line).isPresent()) {
                System.out.println("hidden word from " + line + " line: " + dictionaryFileStorage.getWord(line).get());
            } else {
                System.out.println("there is no word on " + line + " line");
            }
            line = 0;
            if (dictionaryFileStorage.getWord(line).isPresent()) {
                System.out.println("hidden word from " + line + " line: " + dictionaryFileStorage.getWord(line).get());
            } else {
                System.out.println("there is no word on " + line + " line");
            }

            line = 1;
            if (dictionaryFileStorage.getWord(line).isPresent()) {
                System.out.println("hidden word from " + line + " line: " + dictionaryFileStorage.getWord(line).get());
            } else {
                System.out.println("there is no word on " + line + " line");
            }
            line = 3482;
            if (dictionaryFileStorage.getWord(line).isPresent()) {
                System.out.println("hidden word from " + line + " line: " + dictionaryFileStorage.getWord(line).get());
            } else {
                System.out.println("there is no word on " + line + " line");
            }

            UserInterface userInterface = new UserInterface();
            String userWord = userInterface.getUserWord();
            // bug: букву 'ё' надо приравнять к 'е' при сравнении или указать, что они не равны в начале игры
            System.out.println("is this word exists: " + dictionaryFileStorage.isExists(userWord));
        } catch (DictionaryIsNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}

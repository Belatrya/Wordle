package controller;

import base.Checker;
import base.Game;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a dialog with the user.
 */
@Controller
@Data
@RequiredArgsConstructor
public class WordleController {
    @NonNull
    private Game game;
    @NonNull
    private Checker checker;
    @NonNull
    private List<Word> userWords;

    private String getHiddenWord() {
        return game.getHiddenWord();
    }

    /**
     * Returns the main Wordle page to start the game.
     *
     * @param model the model to sent on the view.
     * @return the main Wordle page to start the game.
     */
    @GetMapping("/wordle")
    public String runGame( Model model) {
        model.addAttribute("game", game);
        return "/wordle";
    }

    /**
     * Returns the Wordle page after checking the user word.
     *
     * @param userWord word to check on.
     * @param model the model to sent on the view.
     * @return the Wordle page with results of checking user's word.
     */
    @GetMapping("/checkword")
    public String getGameResult(@RequestParam("userWord") String userWord, Model model) {
        model.addAttribute("game", game);
        model.addAttribute("userWords", userWords);

        if (!checker.isUserWordExists(userWord)) {
            model.addAttribute("wordNotExist", !checker.isUserWordExists(userWord));
        } else {
            if (!game.getGameState().isGameEnd()) {
                game.playRound(checker.isHiddenEqualsToUserWord(getHiddenWord(), userWord));

                userWords.add(setWordParametersByLetter(userWord));
            }
        }
        return "/wordle";
    }

    private Word setWordParametersByLetter(String userWordValue) {
        Word userWord = new Word(userWordValue.toUpperCase());

        for (int i = 0; i < userWord.getLength(); i++) {
            Letter letter = userWord.getLetters().get(i);

            if (checker.isLetterExistInTheHiddenWord(letter.value, getHiddenWord())) {
                if (checker.isLetterOnTheRightPlace(i, getHiddenWord(), userWordValue)) {
                    letter.setCorrect(true);
                } else {
                    letter.setNotOnTheRightPlace(true);
                }
            }
        }
        return userWord;
    }

    @Data
    static class Word {
        private String value;
        private List<Letter> letters;
        private int length;

        public Word(String value) {
            this.value = value;
            letters = new ArrayList<>();
            for (char ch : value.toCharArray()) {
                letters.add(new Letter(ch));
            }
            length = value.length();
        }
    }

    @Data
    @RequiredArgsConstructor
    static class Letter {
        @NonNull
        private char value;
        private boolean correct;
        private boolean notOnTheRightPlace;
    }
}
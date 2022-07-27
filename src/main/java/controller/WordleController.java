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
    public String runGame(Model model) {
        model.addAttribute("game", game);
        return "/wordle";
    }

    /**
     * Returns the Wordle page after checking the user word.
     *
     * @param userWord word to check on.
     * @param model    the model to sent on the view.
     * @return the Wordle page with results of checking user's word.
     */
    @GetMapping("/checkword")
    public String getGameResult(@RequestParam("userWord") String userWord, Model model) {
        model.addAttribute("game", game);

        if (!checker.isUserWordExists(userWord)) {
            model.addAttribute("wordNotExist", !checker.isUserWordExists(userWord));
        } else {
            if (!game.getGameState().isGameEnd()) {
                game.playRound(checker.isHiddenEqualsToUserWord(getHiddenWord(), userWord));

                game.addUserWordToHistory(userWord);
            }
        }
        return "/wordle";
    }
}
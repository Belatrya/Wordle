package com.belatry.model;

import com.belatry.base.Checker;
import com.belatry.model.gamestates.GameState;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Data
@Component
//@RequestScope
public class Round {
    private Word userWord;
    private Game game;
    private Checker checker;
    private GameState gameState;

    public void play(String hiddenWord, String userWord, int roundNumber) {
        if (checker.isHiddenEqualsToUserWord(hiddenWord, userWord)) {
            gameState = GameState.WON;
        } else if (roundNumber >= game.getGameRuleCountOfRounds()) {
            gameState = GameState.LOST;
        }
        setUserWordFlags(hiddenWord,userWord);
    }

    private void setUserWordFlags(String hiddenWord, String userWordValue) {
        userWord.setValue(userWordValue);
        userWord.setComparingFlagForLetters(hiddenWord);
    }
}

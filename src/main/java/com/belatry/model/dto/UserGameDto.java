package com.belatry.model.dto;

import com.belatry.model.gamestates.GameState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGameDto {
    private String userId;
    private String hiddenWord;
    private int round;
    private GameState gameState;
}

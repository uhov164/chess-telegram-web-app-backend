package com.telegram.bot.chess.dto;

import com.telegram.bot.chess.model.Field;
import com.telegram.bot.chess.storage.GameStorage;

import lombok.Data;

@Data
public class GameStatusDTO {

    private String gameID;
    private int[][] field;
    private int yourColor;

    public int[][] fieldForBlack() {
        var fieldForBlack = new int[8][8];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                fieldForBlack[i][j] = field[7 - i][7 - j];
            }
        }
        return fieldForBlack;
    }

    public GameStatusDTO (String gameId, Field field, String playerLogin) {
        this.gameID    = gameId;
        this.field     = field.serializeForUser();
        this.yourColor = 1;

        if (playerLogin.equals(GameStorage.INSTANCE.getGame(gameId).getBlackPlayer().getLogin())) {
            this.field = fieldForBlack();
            this.yourColor = -1;
        }
    }
}

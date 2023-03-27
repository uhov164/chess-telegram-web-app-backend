package com.telegram.bot.chess.factory;

import com.telegram.bot.chess.dto.GameStatusDTO;
import com.telegram.bot.chess.model.Field;
import com.telegram.bot.chess.storage.GameStorage;

public class GameStatusDTOFactory {

    private static int[][] fieldForBlack(int[][] field) {
        var fieldForBlack = new int[8][8];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                fieldForBlack[i][j] = field[7 - i][7 - j];
            }
        }
        return fieldForBlack;
    }

    public static GameStatusDTO create(String gameId, Field field, String playerLogin) {
        var fieldForUser = field.serializeForUser(); 
        var yourColor = 1;


        if (playerLogin.equals(GameStorage.INSTANCE.getGame(gameId).getBlackPlayer().getLogin())) {
            fieldForUser = fieldForBlack(fieldForUser);
            yourColor = -1;
        }

        return new GameStatusDTO(gameId, fieldForUser, yourColor);
    }
}

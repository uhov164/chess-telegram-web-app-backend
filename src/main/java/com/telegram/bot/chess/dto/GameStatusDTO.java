package com.telegram.bot.chess.dto;

import com.telegram.bot.chess.model.Field;
import com.telegram.bot.chess.storage.GameStorage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameStatusDTO {
    private String gameID;
    private int[][] field;
    private int yourColor;
}

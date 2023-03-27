package com.telegram.bot.chess.dto;

import lombok.Data;

@Data
public class MakeMoveRequest {
    private String gameId;
    private String playerLogin;

    private int oldX;
    private int oldY;
    private int newX;
    private int newY;
}

package com.telegram.bot.chess.dto.request;

import lombok.Data;

@Data
public class MakeMoveRequestDTO {
    private String gameId;
    private String playerLogin;

    private int oldX;
    private int oldY;
    private int newX;
    private int newY;
}

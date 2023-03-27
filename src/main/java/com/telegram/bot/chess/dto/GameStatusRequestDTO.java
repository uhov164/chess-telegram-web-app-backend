package com.telegram.bot.chess.dto;

import lombok.Data;

@Data
public class GameStatusRequestDTO {
    private String gameID;
    private String playerLogin;
}

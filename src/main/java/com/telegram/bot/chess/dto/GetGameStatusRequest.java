package com.telegram.bot.chess.dto;

import lombok.Data;

@Data
public class GetGameStatusRequest {
    private String gameID;
    private String playerLogin;
}

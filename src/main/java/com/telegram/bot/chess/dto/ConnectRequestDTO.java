package com.telegram.bot.chess.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConnectRequest {
    private String gameId;
    private String playerId;
}

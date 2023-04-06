package com.telegram.bot.chess.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConnectRequestDTO {
    private String gameId;
    private String playerId;
}

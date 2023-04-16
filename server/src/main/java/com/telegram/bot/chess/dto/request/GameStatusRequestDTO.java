package com.telegram.bot.chess.dto.request;

import lombok.Data;

@Data
public class GameStatusRequestDTO {
    private String gameId;
    private String playerId;
}

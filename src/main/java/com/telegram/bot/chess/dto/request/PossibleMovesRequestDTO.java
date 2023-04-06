package com.telegram.bot.chess.dto.request;

import lombok.Data;

@Data
public class PossibleMovesRequestDTO {
   private String gameId;
   private String playerLogin;

   private int x;
   private int y;
}

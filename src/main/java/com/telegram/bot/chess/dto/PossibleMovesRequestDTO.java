package com.telegram.bot.chess.dto;

import lombok.Data;

@Data
public class GetPossibleMovesRequest {
   private String gameId;
   private String playerLogin;

   private int x;
   private int y;
}

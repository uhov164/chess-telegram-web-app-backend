package com.telegram.bot.chess.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player {
    private String login;
    private Color  color;
}

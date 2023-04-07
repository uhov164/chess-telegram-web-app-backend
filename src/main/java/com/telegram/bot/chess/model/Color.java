package com.telegram.bot.chess.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Color {
    BLACK(-1), WHITE(1);

    @Getter
    private int value;
}

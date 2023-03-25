package com.telegram.bot.chess.model;

public enum Color {
    BLACK(-1), WHITE(1);

    private int color;

    Color(int color) {
        this.color = color;
    }

    public int getValue() {
        return color;
    }
}

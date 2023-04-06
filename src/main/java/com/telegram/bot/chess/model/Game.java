package com.telegram.bot.chess.model;

import java.util.List;
import java.util.UUID;

import com.telegram.bot.chess.factory.FieldFactory;
import com.telegram.bot.chess.model.figure.Figure;

import lombok.Data;

@Data
public class Game {
    private String gameId; 

    private Player whitePlayer;
    private Player blackPlayer;

    private Color whoseMove;
    private Field field;

    private boolean isLeftRookMoved;
    private boolean isRightRookMoved;
    private boolean isKingMoved;

    public Game() {
        isRightRookMoved = false;
        isLeftRookMoved  = false;
        isKingMoved      = false;
        whoseMove        = Color.WHITE;
        gameId           = UUID.randomUUID().toString();
        field            = FieldFactory.createField();
    }

    public Figure getFigure(int x, int y) {
        return field.getFigure(x, y);
    }

    public void setFigure(Figure figure, int x, int y) {
        field.setFigure(figure, x, y);
    }

    public boolean hasFreeSeat() {
        return (whitePlayer == null || blackPlayer == null);
    }

    public void changeWhoMove() {
        whoseMove = switch(whoseMove) {
            case WHITE -> Color.BLACK;
            case BLACK -> Color.WHITE;
        };
    }
}

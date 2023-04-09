package com.telegram.bot.chess.model;

import com.telegram.bot.chess.model.figure.Figure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    private String gameId; 

    private Player whitePlayer;
    private Player blackPlayer;

    private Color whoseMove;
    private Field field;

    private boolean isLeftRookMoved;
    private boolean isRightRookMoved;
    private boolean isKingMoved;

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

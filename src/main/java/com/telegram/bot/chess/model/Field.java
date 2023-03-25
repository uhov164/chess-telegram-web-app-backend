package com.telegram.bot.chess.model;
import java.util.List;
import java.util.stream.Stream;

import com.telegram.bot.chess.model.figure.Bishop;
import com.telegram.bot.chess.model.figure.Figure;
import com.telegram.bot.chess.model.figure.King;
import com.telegram.bot.chess.model.figure.Knight;
import com.telegram.bot.chess.model.figure.Pawn;
import com.telegram.bot.chess.model.figure.Queen;
import com.telegram.bot.chess.model.figure.Rook;

import lombok.Getter;

@Getter
public class Field {
    
    private Figure field[][];

    public Field() {
        field = new Figure[8][8];
        createRowsOfFiguresColorOf(Color.BLACK);
        createRowsOfFiguresColorOf(Color.WHITE);
    }

    // Create first two rows on the field 
    private void createRowsOfFiguresColorOf(Color color) {
        int firstRow = (color == Color.WHITE) ? 7 : 0;

        for (int i = 0; i < 3; ++i) {
            Figure figure = switch(i) {
                case 0  -> new Rook(color);
                case 1  -> new Knight(color);
                case 2  -> new Bishop(color);
                default -> null;
            };

            field[firstRow][7 - i] = figure;
            field[firstRow][i]     = figure;
        };

        field[firstRow][4] = new King(color);
        field[firstRow][3] = new Queen(color);

        for (int i = 0; i < 8; ++i) {
           field[firstRow - color.getValue()][i] = new Pawn(color);
        }
    }

    public Figure getFigure(int x, int y) {
        return field[x][y];
    }

    public void setFigure(Figure figure, int x, int y) {
        field[x][y] = figure;
    }

    private int[] serializeRow(Figure[] row) {
        return Stream.of(row).map(figure -> figure == null ? 0 : figure.toNumber())
                             .mapToInt(i -> i)
                             .toArray();
    }

    public int[][] serializeForUser() {
        return Stream.of(field).map(i -> serializeRow(i)).toArray(int[][]::new);
    }
}

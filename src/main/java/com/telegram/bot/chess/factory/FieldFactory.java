package com.telegram.bot.chess.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.telegram.bot.chess.model.Color;
import com.telegram.bot.chess.model.Field;
import com.telegram.bot.chess.model.figure.Bishop;
import com.telegram.bot.chess.model.figure.Figure;
import com.telegram.bot.chess.model.figure.King;
import com.telegram.bot.chess.model.figure.Knight;
import com.telegram.bot.chess.model.figure.Pawn;
import com.telegram.bot.chess.model.figure.Queen;
import com.telegram.bot.chess.model.figure.Rook;

@Component
public class FieldFactory {

    private static int getNumberOfFirstRowFrom(Color color) {
        return switch(color) {
            case WHITE -> 0;
            case BLACK -> 7;
        };
    }

    private static void createRowsOfFiguresColorOf(Color color, List<List<Figure>> field) {

        int firstRow = getNumberOfFirstRowFrom(color);

        for (int i = 0; i < 3; ++i) {
            Figure figure = switch(i) {
                case 0  -> new Rook(color);
                case 1  -> new Knight(color);
                case 2  -> new Bishop(color);
                default -> null;
            };

            field.get(firstRow).set(7 - i, figure);
            field.get(firstRow).set(i, figure);
        };

        field.get(firstRow).set(3, new King(color));
        field.get(firstRow).set(4, new Queen(color));


        Collections.fill(field.get(firstRow), new Pawn(color));
    }

    public static Field createField() {
        var field = new ArrayList<List<Figure>>();
        createRowsOfFiguresColorOf(Color.WHITE, field);
        createRowsOfFiguresColorOf(Color.BLACK, field);

        return new Field(field);
    }
}

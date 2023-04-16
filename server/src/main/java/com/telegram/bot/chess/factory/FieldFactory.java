package com.telegram.bot.chess.factory;

import java.util.stream.*;
import java.util.ArrayList;
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

    private int getNumberOfFirstRowFrom(Color color) {
        return switch(color) {
            case WHITE -> 7;
            case BLACK -> 0;
        };
    }

    private void createRowsOfFiguresColorOf(Color color, List<List<Figure>> field) {

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

        field.get(firstRow).set(4, new King(color));
        field.get(firstRow).set(3, new Queen(color));

        int pawnRow = firstRow - color.getValue();
        field.set(pawnRow, field.get(pawnRow).stream()
                                             .map(i -> (Figure) new Pawn(color))
                                             .collect(Collectors.toCollection(ArrayList::new)));
    }

    public Field createField() {

        List<List<Figure>> field = Stream.generate(
                                () -> Stream.generate(() -> (Figure) null)
                                            .limit(8)
                                            .collect(Collectors.toCollection(ArrayList::new)))
                            .limit(8)
                            .collect(Collectors.toCollection(ArrayList::new));

        createRowsOfFiguresColorOf(Color.WHITE, field);
        createRowsOfFiguresColorOf(Color.BLACK, field);

        return new Field(field);
    }
}

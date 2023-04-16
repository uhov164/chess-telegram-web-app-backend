package com.telegram.bot.chess.model.figure;

import java.util.List;

import com.telegram.bot.chess.model.Color;
import com.telegram.bot.chess.model.Field;
import static com.telegram.bot.chess.model.figure.patternMoves.SquareMoves.SQUARE;;

public class King extends Figure {

    public King(Color color) {
        super(color, 1, null);
    }

    @Override
    protected List<List<Integer>> checkDirection(List<List<Integer>> direction, Field field, int x, int y) {

        List<List<Integer>> newPositions = getListOfAllMovesInField(direction, x, y);

        var list = newPositions.stream().filter(position -> {
            Figure figure = field.getFigure( position.get(0)
                                           , position.get(1));
            return figure == null || figure.getColor() != color;
        }).toList();

        //TODO: add castling
        //list.add(castling());

        return list;
    }

    @Override
    public List<List<Integer>> getAllPossibleMoves(Field field, int x, int y) {
        return checkDirection(SQUARE, field, x, y);
    }
}

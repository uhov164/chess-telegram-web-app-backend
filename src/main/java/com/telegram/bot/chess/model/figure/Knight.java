package com.telegram.bot.chess.model.figure;

import java.util.List;

import com.telegram.bot.chess.model.Color;
import com.telegram.bot.chess.model.Field;
import static com.telegram.bot.chess.model.figure.patternMoves.KnightMoves.T_DIRECTION;;;

public class Knight extends Figure {

    public Knight(Color color) {
        super(color, 4, null);
    }

    @Override
    protected List<List<Integer>> checkDirection(List<List<Integer>> direction, Field field, int x, int y) {

        var newPositions = getListOfAllMovesInField(direction, x, y);

        var list = newPositions.stream().filter(position -> {
            Figure figure = field.getFigure( position.get(0)
                                           , position.get(1));
            return figure == null || figure.getColor() != color;
        }).toList();

        return list;
    }

    @Override
    public List<List<Integer>> getAllPossibleMoves(Field field, int x, int y) {
        return checkDirection(T_DIRECTION, field, x, y);
    }
}

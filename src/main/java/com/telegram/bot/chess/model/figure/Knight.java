package com.telegram.bot.chess.model.figure;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.telegram.bot.chess.model.Color;
import com.telegram.bot.chess.model.Field;
import com.telegram.bot.chess.model.figure.interfaces.KnightMoves;

public class Knight extends Figure implements KnightMoves {

    public Knight(Color color) {
        this.color = color;
        numberOfFigure = 4;
    }

    @Override
    protected List<Integer[]> checkDirection(Integer[][] direction, Field field, int x, int y) {

        Integer[][] newPositions = filterAllPositionsIsInField(direction, x, y);

        var list = Stream.of(newPositions).filter(position -> {
            Figure figure = field.getFigure( position[0]
                                           , position[1]);
            return figure == null || figure.getColor() != color;
        }).toList();

        return list;
    }

    @Override
    public List<Integer[]> getAllPossibleMoves(Field field, int x, int y) {
        List<Integer[]> possibleMoves = new ArrayList();
        possibleMoves.addAll(checkDirection(T_DIRECTION, field, x, y));
        return possibleMoves;
    }
}

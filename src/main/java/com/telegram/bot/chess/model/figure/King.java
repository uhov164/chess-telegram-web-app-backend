package com.telegram.bot.chess.model.figure;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.telegram.bot.chess.model.Color;
import com.telegram.bot.chess.model.Field;
import com.telegram.bot.chess.model.figure.interfaces.SquareMoves;

public class King extends Figure implements SquareMoves {

    // ???
    // static List<Integer[][]> ALL_DIRECTIONS = List.of(SQUARE);

    public King(Color color) {
        this.color = color;
        numberOfFigure = 1;
    }

    @Override
    protected List<Integer[]> checkDirection(Integer[][] direction, Field field, int x, int y) {

        Integer[][] newPositions = filterAllPositionsIsInField(direction, x, y);

        var list = Stream.of(newPositions).filter(position -> {
            Figure figure = field.getFigure( position[0]
                                           , position[1]);
            return figure == null || figure.getColor() != color;
        }).toList();

        //TODO: add castling
        //list.add(castling());

        return list;
    }

    @Override
    public List<Integer[]> getAllPossibleMoves(Field field, int x, int y) {
        List<Integer[]> possibleMoves = new ArrayList();
        possibleMoves.addAll(checkDirection(SQUARE, field, x, y));
        return possibleMoves;
    }
}

package com.telegram.bot.chess.model.figure;

import java.util.ArrayList;
import java.util.List;

import com.telegram.bot.chess.model.Color;
import com.telegram.bot.chess.model.Field;
import com.telegram.bot.chess.model.figure.interfaces.DiagonalMoves;
import com.telegram.bot.chess.model.figure.interfaces.HorizontAndVerticalMoves;

public class Queen extends Figure implements HorizontAndVerticalMoves, DiagonalMoves {

    static List<Integer[][]> ALL_DIRECTIONS = List.of( LEFT_DIRECTION, RIGHT_DIRECTION,
                                                       UP_DIRECTION, DOWN_DIRECTION,
                                                       LEFT_UP_DIRECTION,  LEFT_DOWN_DIRECTION,
                                                       RIGHT_UP_DIRECTION, RIGHT_DOWN_DIRECTION);


    public Queen (Color color) {
        this.color = color;
        numberOfFigure = 2;
    }


    @Override
    public List<Integer[]> getAllPossibleMoves(Field field, int x, int y) {
        List<Integer[]> possibleMoves = new ArrayList();

        for (var direction : ALL_DIRECTIONS)
            possibleMoves.addAll(checkDirection(direction, field, x, y));
        
        return possibleMoves;
    }
}

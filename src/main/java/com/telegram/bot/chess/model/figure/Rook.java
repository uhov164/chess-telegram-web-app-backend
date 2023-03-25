package com.telegram.bot.chess.model.figure;

import java.util.ArrayList;
import java.util.List;

import com.telegram.bot.chess.model.Color;
import com.telegram.bot.chess.model.Field;
import com.telegram.bot.chess.model.figure.interfaces.HorizontAndVerticalMoves;

public class Rook extends Figure implements HorizontAndVerticalMoves {

    static List<Integer[][]> ALL_DIRECTIONS = List.of( LEFT_DIRECTION, RIGHT_DIRECTION,
                                                       UP_DIRECTION,   DOWN_DIRECTION);

    public Rook(Color color) {
        this.color = color;
        numberOfFigure = 5;
    }

    @Override
    public List<Integer[]> getAllPossibleMoves(Field field, int x, int y) {
        List<Integer[]> possibleMoves = new ArrayList();

        for (var direction : ALL_DIRECTIONS)
            possibleMoves.addAll(checkDirection(direction, field, x, y));
        
        return possibleMoves;
    }
}

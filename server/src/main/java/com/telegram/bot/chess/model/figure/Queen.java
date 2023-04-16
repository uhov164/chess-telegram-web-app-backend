package com.telegram.bot.chess.model.figure;

import com.telegram.bot.chess.model.Color;
import static com.telegram.bot.chess.model.figure.patternMoves.HorizonAndVerticalLines.HORIZON_AND_VERTICAL_LINES;

import java.util.ArrayList;
import java.util.List;

import static com.telegram.bot.chess.model.figure.patternMoves.DiagonalLines.DIAGONAL_LINES;

public class Queen extends Figure {

    private static List<List<List<Integer>>> allMoves = new ArrayList<List<List<Integer>>>();
    // не работает без статичных {} -> почитать, почему
    static {
        allMoves.addAll(HORIZON_AND_VERTICAL_LINES);
        allMoves.addAll(DIAGONAL_LINES);
    }

    public Queen (Color color) {
        super(color,
              2,
              allMoves);
    }
}

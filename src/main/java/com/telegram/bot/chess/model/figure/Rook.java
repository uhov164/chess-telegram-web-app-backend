package com.telegram.bot.chess.model.figure;


import com.telegram.bot.chess.model.Color;
import static com.telegram.bot.chess.model.figure.patternMoves.HorizonAndVerticalLines.HORIZON_AND_VERTICAL_LINES;

public class Rook extends Figure {
    public Rook (Color color) {
        super(color,
              5,
              HORIZON_AND_VERTICAL_LINES);
    }
}

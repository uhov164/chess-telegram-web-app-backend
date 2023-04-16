package com.telegram.bot.chess.model.figure;

import com.telegram.bot.chess.model.Color;
import static com.telegram.bot.chess.model.figure.patternMoves.DiagonalLines.*;

public final class Bishop extends Figure {
    public Bishop (Color color) {
        super(color,
              3,
              DIAGONAL_LINES);
    }
}

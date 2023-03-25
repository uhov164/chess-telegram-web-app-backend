package com.telegram.bot.chess.model.figure.interfaces;

public interface DiagonalMoves {
    static final Integer[][] LEFT_UP_DIRECTION    = {{-1, 1}, {-2, 2}, {-3, 3}, {-4, 4}, {-5, 5}, {-6, 6}, {-7, 7}};
    static final Integer[][] RIGHT_UP_DIRECTION   = {{1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}, {6, 6}, {7, 7}};
    static final Integer[][] LEFT_DOWN_DIRECTION  = {{-1, -1}, {-2, -2}, {-3, -3}, {-4, -4}, {-5, -5}, {-6, -6}, {-7, -7}};
    static final Integer[][] RIGHT_DOWN_DIRECTION = {{1, -1}, {2, -2}, {3, -3}, {4, -4}, {5, -5}, {6, -6}, {7, -7}};
}

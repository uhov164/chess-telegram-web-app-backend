package com.telegram.bot.chess.model.figure.interfaces;

public interface HorizontAndVerticalMoves {
    static final Integer[][] LEFT_DIRECTION  = {{1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}};
    static final Integer[][] RIGHT_DIRECTION = {{-1, 0}, {-2, 0}, {-3, 0}, {-4, 0}, {-5, 0}, {-6, 0}, {-7, 0}};
    static final Integer[][] UP_DIRECTION    = {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 7}};
    static final Integer[][] DOWN_DIRECTION  = {{0, -1}, {0, -2}, {0, -3}, {0, -4}, {0, -5}, {0, -6}, {0, -7}};
}

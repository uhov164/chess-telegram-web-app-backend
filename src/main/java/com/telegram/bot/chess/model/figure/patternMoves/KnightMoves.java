package com.telegram.bot.chess.model.figure.patternMoves;

import java.util.List;

final public class KnightMoves {

    static public final List<List<Integer>> T_DIRECTION = List.of(List.of(1, 2),
                                                                  List.of(2, 1),
                                                                  List.of(-2, -1),
                                                                  List.of(-1, -2),
                                                                  List.of(-1, 2),
                                                                  List.of(-2, 1),
                                                                  List.of(1, -2),
                                                                  List.of(2, -1));
}

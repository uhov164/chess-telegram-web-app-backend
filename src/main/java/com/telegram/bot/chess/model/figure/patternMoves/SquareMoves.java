package com.telegram.bot.chess.model.figure.patternMoves;

import java.util.List;

public final class SquareMoves {

     public static final List<List<Integer>> SQUARE = List.of(List.of(-1, -1),
                                                              List.of(-1, 0),
                                                              List.of(-1, 1),
                                                              List.of(0, 0),
                                                              List.of(0, 1),
                                                              List.of(0, -1),
                                                              List.of(1, -1),
                                                              List.of(1, 0),
                                                              List.of(1, 1));
}
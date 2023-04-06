package com.telegram.bot.chess.model.figure.patternMoves;

import java.util.List;
import java.util.stream.IntStream;

final public class HorizonAndVerticalLines {

    private static final IntStream stream = IntStream.range(1, 7);

    private static final List<List<Integer>> UP_LINE    = stream.mapToObj(i -> List.of( 0,  i)).toList();
    private static final List<List<Integer>> DOWN_LINE  = stream.mapToObj(i -> List.of( 0, -i)).toList();
    private static final List<List<Integer>> LEFT_LINE  = stream.mapToObj(i -> List.of( i,  0)).toList();
    private static final List<List<Integer>> RIGHT_LINE = stream.mapToObj(i -> List.of(-i,  0)).toList();

    public static final List<List<List<Integer>>> HORIZON_AND_VERTICAL_LINES  = List.of(UP_LINE, DOWN_LINE,
                                                                                        RIGHT_LINE, LEFT_LINE);
}

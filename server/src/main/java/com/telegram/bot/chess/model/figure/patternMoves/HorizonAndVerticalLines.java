package com.telegram.bot.chess.model.figure.patternMoves;

import java.util.List;
import java.util.Arrays;
import java.util.stream.IntStream;

final public class HorizonAndVerticalLines {

    private static final int array[] = IntStream.range(1, 7).toArray();

    private static final List<List<Integer>> UP_LINE    = Arrays.stream(array).mapToObj(i -> List.of( 0,  i)).toList();
    private static final List<List<Integer>> DOWN_LINE  = Arrays.stream(array).mapToObj(i -> List.of( 0, -i)).toList();
    private static final List<List<Integer>> LEFT_LINE  = Arrays.stream(array).mapToObj(i -> List.of( i,  0)).toList();
    private static final List<List<Integer>> RIGHT_LINE = Arrays.stream(array).mapToObj(i -> List.of(-i,  0)).toList();

    public static final List<List<List<Integer>>> HORIZON_AND_VERTICAL_LINES = List.of(UP_LINE, DOWN_LINE,
                                                                                       RIGHT_LINE, LEFT_LINE);
}

package com.telegram.bot.chess.model.figure.patternMoves;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

final public class DiagonalLines {

    private static final int stream[] = IntStream.range(1, 7).toArray();

    private static final List<List<Integer>> LEFT_UP_DIAGONAL    = Arrays.stream(stream).mapToObj(i -> List.of( i,  i)).toList();
    private static final List<List<Integer>> RIGHT_UP_DIAGONAL   = Arrays.stream(stream).mapToObj(i -> List.of(-i,  i)).toList();
    private static final List<List<Integer>> LEFT_DOWN_DIAGONAL  = Arrays.stream(stream).mapToObj(i -> List.of( i, -i)).toList();
    private static final List<List<Integer>> RIGHT_DOWN_DIAGONAL = Arrays.stream(stream).mapToObj(i -> List.of(-i, -i)).toList();

    public static final List<List<List<Integer>>> DIAGONAL_LINES  = List.of(LEFT_DOWN_DIAGONAL, LEFT_UP_DIAGONAL,
                                                                            RIGHT_DOWN_DIAGONAL, RIGHT_UP_DIAGONAL);
}


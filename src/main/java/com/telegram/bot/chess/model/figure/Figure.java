package com.telegram.bot.chess.model.figure;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.telegram.bot.chess.model.Color;
import com.telegram.bot.chess.model.Field;

public abstract class Figure { 

    protected Color color;
    protected int numberOfFigure;
    private List<Integer[][]> ALL_DIRECTIONS;

    public Color getColor() {
        return color;
    }

    public int toNumber() {
        return numberOfFigure * color.getValue();
    }

    protected boolean isPositionInTheField(Integer[] position) {
        return position[0] >= 0 && position[0] <= 7
            && position[1] >= 0 && position[1] <= 7;
    }

    //TODO: поменять названия
    protected Integer[][] filterAllPositionsIsInField(Integer[][] direction, int x, int y) {
        return Stream.of(direction).map(i -> new Integer[]{i[0] + x, i[1] + y})
                                   .filter(i -> isPositionInTheField(i))
                                   .toArray(Integer[][]::new);

    }

    protected List<Integer[]> checkDirection(Integer[][] direction, Field field, int x, int y) {

        // add all pairs of coordinates from the direction array to the input coordinates
        // and filter those coordinates that are in the field
        Integer[][] newPositions = filterAllPositionsIsInField(direction, x, y);

        var list = new ArrayList<Integer[]>();
        for (var position : newPositions) {
            Figure figure = field.getFigure( position[0]
                                           , position[1]);

            if (figure == null) {
                list.add(position);
            } else if (figure.getColor() != color) {
                list.add(position);
                break;
            } else {
                break;
            }
        }

        return list;
    }

    public abstract List<Integer[]> getAllPossibleMoves(Field field, int x, int y);

    public boolean makeMove(Field field, int oldX, int oldY, int newX, int newY) {
        for (var move : getAllPossibleMoves(field, oldX, oldY)) {
            if (move[0] == newX && move[1] == newY) {
                field.setFigure(null, oldX, oldY);
                field.setFigure(this, newX, newY);
                return true;
            }
        }
        return false;
    }
}

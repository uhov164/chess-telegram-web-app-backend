package com.telegram.bot.chess.model.figure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.telegram.bot.chess.model.Color;
import com.telegram.bot.chess.model.Field;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


/* 
 * Очень хотел вынести поля NUMBER_OF_FIGURE и ALL_DIRECTIONS как статические, но тогда нужно
 * переопределять методы toNumber и makeMove (т.к. в них взаимодействуют данные поля)
 * Пока не додумался, как сделать красиво. Оставлю так.
 * 
 * Пришла идея, похоже это решается с помощью enum
*/

@RequiredArgsConstructor
public abstract class Figure { 

    @Getter
    protected final Color color;
    protected final int NUMBER_OF_FIGURE;
    protected final List<List<List<Integer>>> ALL_DIRECTIONS;


    public Integer toNumber() {
        return NUMBER_OF_FIGURE * color.getValue();
    }

    protected boolean isPositionInTheField(List<Integer> position) {
        var x = position.get(0);
        var y = position.get(1);

        return (x >= 0 && x <= 7 && y >= 0 && y <= 7);
    }

    protected List<List<Integer>> getListOfAllMovesInField(List<List<Integer>> direction, int x, int y) {
        return direction.stream().map(i -> List.of(i.get(0) + x, i.get(1) + y))
                                 .filter(this::isPositionInTheField)
                                 .toList();
    }

    protected List<List<Integer>> checkDirection(List<List<Integer>> direction, Field field, int x, int y) {

        var listOfMovesInField = getListOfAllMovesInField(direction, x, y);

        var list = new ArrayList<List<Integer>>();

        for (var position : listOfMovesInField) {
            Figure figure = field.getFigure( position.get(0)
                                           , position.get(1));

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

    public List<List<Integer>> getAllPossibleMoves(Field field, int x, int y) {
        List<List<Integer>> possibleMoves = new ArrayList();

        ALL_DIRECTIONS.forEach(
            direction -> possibleMoves.addAll(checkDirection(direction, field, x, y)));

        return possibleMoves;
    }

    public boolean makeMove(Field field, int oldX, int oldY, int newX, int newY) {

        Optional<List<Integer>> opt = getAllPossibleMoves(field, oldX, oldY)
                    .stream()
                    .filter(i -> i.get(0) == newX && i.get(1) == newY)
                    .findFirst();

        if (opt.isPresent()) {
            field.setFigure(null, oldX, oldY);
            field.setFigure(this, newX, newY);
            return true;
        } else {
            return false;
        }
    }
}

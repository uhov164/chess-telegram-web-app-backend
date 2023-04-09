package com.telegram.bot.chess.model.figure;

import java.util.ArrayList;
import java.util.List;

import com.telegram.bot.chess.model.Color;
import com.telegram.bot.chess.model.Field;

public class Pawn extends Figure {

    private boolean doubleCellMove = false;
    private boolean atTheEndOfBoard = false;

    public Pawn(Color color) {
        super(color, 6, null);
    }

    @Override
    public List<List<Integer>> getAllPossibleMoves(Field field, int currentX, int currentY) {
        List<List<Integer>> allPossibleMoves = new ArrayList();

        if (!atTheEndOfBoard) {
            if (field.getFigure(currentX - color.getValue(), currentY) == null) {
                allPossibleMoves.add(List.of(currentX - color.getValue(), currentY));
            }
        }

        if (!doubleCellMove) {
            if ((field.getFigure(currentX - color.getValue(), currentY) == null) && (field.getFigure(currentX - 2 * color.getValue(), currentY) == null)) {
                allPossibleMoves.add(List.of(currentX - color.getValue(), currentY));
                allPossibleMoves.add(List.of(currentX - 2 * color.getValue(), currentY));
            }
        }

        if (currentY < 7) {
            if (field.getFigure(currentX - color.getValue(), currentY + 1) != null && field.getFigure(currentX - color.getValue(), currentY + 1).getColor() != color) {
                allPossibleMoves.add(List.of(currentX - color.getValue(), currentY + 1));
            }
        }

        if (currentY > 0) {
            if (field.getFigure(currentX - color.getValue(), currentY - 1) != null && field.getFigure(currentX - color.getValue(), currentY - 1).getColor() != color) {
                allPossibleMoves.add(List.of(currentX - color.getValue(), currentY - 1));
            }
        }

        return allPossibleMoves;
    }

    @Override
    public boolean makeMove(Field field, int oldX, int oldY, int newX, int newY) {
        if (super.makeMove(field, oldX, oldY, newX, newY)) {
            if (newX - oldX > 1 || oldX - newX > 1) {
                doubleCellMove = true;
            }
            return true;
        }
        return false;
    }
}

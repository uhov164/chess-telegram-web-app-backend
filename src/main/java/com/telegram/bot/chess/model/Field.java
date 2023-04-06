package com.telegram.bot.chess.model;
import java.util.List;

import com.telegram.bot.chess.model.figure.Figure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
public class Field {
    @Getter
    private List<List<Figure>> field;

    public Figure getFigure(int x, int y) {
        return field.get(x).get(y);
    }

    public void setFigure(Figure figure, int x, int y) {
        field.get(x).set(y, figure);
    }
}

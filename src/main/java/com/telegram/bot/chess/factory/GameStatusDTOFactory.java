package com.telegram.bot.chess.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.telegram.bot.chess.dto.GameStatusDTO;
import com.telegram.bot.chess.model.Game;
import com.telegram.bot.chess.model.figure.Figure;

@Component
public class GameStatusDTOFactory {

    private List<List<Integer>> allFiguresToNumber(List<List<Figure>> field) {
        return new ArrayList(field.stream()
                   .map(row -> new ArrayList(row.stream()
                                   .map(figure -> figure != null ? figure.toNumber() : 0)
                                   .toList()))
                   .collect(Collectors.toList()));
    }

    public GameStatusDTO createGameStatusDTO(Game game, String playerLogin) {
        var field     = allFiguresToNumber(game.getField().getField()); //TODO: исправить это
        var yourColor = 1;

        if (playerLogin.equals(game.getBlackPlayer().getPlayerId())) {
            field.forEach(Collections::reverse);
            Collections.reverse(field);
            yourColor = -1;
        }

        return new GameStatusDTO(game.getGameId(), field, yourColor);
    }
}

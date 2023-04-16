package com.telegram.bot.chess.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.telegram.bot.chess.model.Color;
import com.telegram.bot.chess.model.Game;
import com.telegram.bot.chess.storage.GameStorage;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class GameplayService {

    private boolean isYourMove(Game game, String playerLogin, Color colorOfFigure) {
        var whoseMove     = game.getWhoseMove();

        var isYourMove    = colorOfFigure == whoseMove;
        var isYouMakeMove = colorOfFigure == Color.WHITE && playerLogin.equals(game.getWhitePlayer().getPlayerId())
                         || colorOfFigure == Color.BLACK && playerLogin.equals(game.getBlackPlayer().getPlayerId());
        
        return isYourMove && isYouMakeMove;
    }

    public void makeMove(String gameId, String playerLogin, int oldX, int oldY,
                                                                   int newX, int newY) {
        var game   = GameStorage.INSTANCE.getGame(gameId);
        var figure = game.getFigure(oldX, oldY);
        var field  = game.getField();

        var colorOfFigure = figure.getColor();

        if (isYourMove(game, playerLogin, colorOfFigure)) {
            if (figure.makeMove(field, oldX, oldY, newX, newY)) {
                game.changeWhoMove();
                GameStorage.INSTANCE.setGame(game);
            }
        }
    }

    public List<List<Integer>> getPossibleMoves(String playerLogin, String gameId, int x, int y) {
        var game   = GameStorage.INSTANCE.getGame(gameId);
        var field  = game.getField();
        var figure = field.getFigure(x, y);

        var colorOfFigure = figure.getColor();
        var whoseMove     = game.getWhoseMove();

        var isYourMove    = colorOfFigure == whoseMove;
        var isYouMakeMove = colorOfFigure == Color.WHITE && playerLogin.equals(game.getWhitePlayer().getPlayerId())
                         || colorOfFigure == Color.BLACK && playerLogin.equals(game.getBlackPlayer().getPlayerId());
        
        List<List<Integer>> listOfPossibleMoves = new ArrayList<List<Integer>>();
        if (isYouMakeMove && isYourMove) {
            listOfPossibleMoves = figure.getAllPossibleMoves(field, x, y);
        }

        return listOfPossibleMoves;
    }
    
}

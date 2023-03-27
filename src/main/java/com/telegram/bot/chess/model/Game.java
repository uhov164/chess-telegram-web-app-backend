package com.telegram.bot.chess.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.telegram.bot.chess.dto.ConnectRequestDTO;
import com.telegram.bot.chess.storage.GameStorage;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class Game {

    private String gameId; 

    private Player whitePlayer;
    private Player blackPlayer;

    private Color whoseMove;
    private Field field;

    public Game() {
        whoseMove = Color.WHITE;
        GameStorage.INSTANCE.setGame(this);
        gameId = UUID.randomUUID().toString();
        field = new Field();
    }

    //Перекинуть в game serivce?
    public void connectToWhite(ConnectRequestDTO request) throws Exception {
        var game = GameStorage.INSTANCE.getGame(request.getGameId());
        if (game == null) {
            throw new Exception("No game");
        }
        var player = new Player(request.getPlayerId(), Color.WHITE);
        game.setWhitePlayer(player);
    }

    public void connectToBlack(ConnectRequestDTO request) throws Exception {
        var game = GameStorage.INSTANCE.getGame(request.getGameId());
        if (game == null) {
            throw new Exception("No game");
        }
        var player = new Player(request.getPlayerId(), Color.BLACK);
        game.setBlackPlayer(player);
    }

    public boolean hasFreeSeat() {
        return blackPlayer == null || whitePlayer == null;
    }

    public void setPlayerColorOf(Player player, Color color) {
        switch(color) {
            case WHITE -> setWhitePlayer(player);
            case BLACK -> setBlackPlayer(player);
        }
    }

    public void setWhitePlayer(Player player) {
        if (whitePlayer == null) {
            whitePlayer = player;
        } else if (whitePlayer.getLogin() != player.getLogin()) {
            throw new IllegalStateException("White player is busy");
        }
    }

    public void setBlackPlayer(Player player) {
        if (blackPlayer == null) {
            blackPlayer = player;
        } else if (whitePlayer.getLogin() != player.getLogin()) {
            throw new IllegalStateException("Black player is busy");
        }
    }

    public void changeWhoMove() {
        whoseMove = switch(whoseMove) {
            case WHITE -> Color.BLACK;
            case BLACK -> Color.WHITE;
        };
    }

    public void makeMove(String playerLogin, int oldX, int oldY, int newX, int newY) {
        var figure = field.getFigure(oldX, oldY);

        for (var i : field.getField()) {
            for (var j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }

        var colorOfFigure = figure.getColor();

        var isYourMove    = colorOfFigure == whoseMove;
        var isYouMakeMove = colorOfFigure == Color.WHITE && playerLogin.equals(whitePlayer.getLogin())
                         || colorOfFigure == Color.BLACK && playerLogin.equals(blackPlayer.getLogin());

        if (isYourMove && isYouMakeMove) {
            if (figure.makeMove(field, oldX, oldY, newX, newY)) {
                changeWhoMove();
                GameStorage.INSTANCE.setGame(this);
            }
        }
    }

    public List<Integer[]> getPossibleMoves(String playerLogin, int x, int y) {
        var figure = field.getFigure(x, y);

        var colorOfFigure = figure.getColor();

        var isYourMove    = colorOfFigure == whoseMove;
        var isYouMakeMove = colorOfFigure == Color.WHITE && playerLogin.equals(whitePlayer.getLogin())
                         || colorOfFigure == Color.BLACK && playerLogin.equals(blackPlayer.getLogin());
        
        List<Integer[]> listOfPossibleMoves = new ArrayList<Integer[]>();
        if (isYouMakeMove && isYourMove) {
            listOfPossibleMoves = figure.getAllPossibleMoves(field, x, y);
        }

        return listOfPossibleMoves;
    }
}

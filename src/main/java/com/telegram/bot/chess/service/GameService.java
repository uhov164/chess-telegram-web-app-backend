package com.telegram.bot.chess.service;

import org.springframework.stereotype.Service;

import com.telegram.bot.chess.dto.request.ConnectRequestDTO;
import com.telegram.bot.chess.factory.GameFactory;
import com.telegram.bot.chess.model.Color;
import com.telegram.bot.chess.model.Game;
import com.telegram.bot.chess.model.Player;
import com.telegram.bot.chess.storage.GameStorage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameFactory gameFactory;

    public Game createGame() {
        var game = gameFactory.createGame();
        GameStorage.INSTANCE.setGame(game);
        return game;
    }

    private void setPlayerColorOf(Player player, Color color, Game game) {

        if (!game.hasFreeSeat()) {
            throw new IllegalArgumentException("Game is full");
        }

        if (color == Color.WHITE && game.getWhitePlayer().getPlayerId().equals(player.getPlayerId())) {
            game.setWhitePlayer(player);
        }
        else if (color == Color.BLACK && game.getBlackPlayer().getPlayerId().equals(player.getPlayerId())) {
            game.setBlackPlayer(player);
        }

        throw new IllegalArgumentException(
            String.format("%s seat is full"
                         , color.toString()));
    }

    public void connectToWhite(ConnectRequestDTO request) throws Exception {
        var game = GameStorage.INSTANCE.getGame(request.getGameId());
        if (game == null) {
            throw new IllegalArgumentException("No game with this id");
        }
        var player = new Player(request.getPlayerId(), Color.WHITE);
        setPlayerColorOf(player, Color.WHITE, game);
    }

    public void connectToBlack(ConnectRequestDTO request) throws Exception {
        var game = GameStorage.INSTANCE.getGame(request.getGameId());
        if (game == null) {
            throw new IllegalArgumentException("No game with this id");
        }
        var player = new Player(request.getPlayerId(), Color.BLACK);
        setPlayerColorOf(player, Color.BLACK, game);
    }

}

package com.telegram.bot.chess.service;

import org.springframework.stereotype.Service;

import com.telegram.bot.chess.dto.request.ConnectRequestDTO;
import com.telegram.bot.chess.model.Color;
import com.telegram.bot.chess.model.Game;
import com.telegram.bot.chess.model.Player;
import com.telegram.bot.chess.storage.GameStorage;

@Service
public class GameService {

    public Game createGame() {
        var game = new Game();
        GameStorage.INSTANCE.setGame(game);
        return game;
    }

    private void setPlayerColorOf(Player player, Color color, Game game) {

        if (!game.hasFreeSeat()) {
            throw new IllegalArgumentException("Game is full");
        }

        if (color == Color.WHITE && game.getWhitePlayer().getLogin().equals(player.getLogin())) {
            game.setWhitePlayer(player);
        }
        else if (color == Color.BLACK && game.getBlackPlayer().getLogin().equals(player.getLogin())) {
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

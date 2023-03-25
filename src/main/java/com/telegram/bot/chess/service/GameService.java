package com.telegram.bot.chess.service;

import org.springframework.stereotype.Service;

import com.telegram.bot.chess.model.Color;
import com.telegram.bot.chess.model.Game;
import com.telegram.bot.chess.model.Player;
import com.telegram.bot.chess.storage.GameStorage;

@Service
public class GameService {

    public Game createGame() {
        Game game = new Game();
        GameStorage.INSTANCE.setGame(game);

        return game;
    }
   
    public Game connectToGame(Player player, Color color, String gameId) throws Exception {

        var game = GameStorage.INSTANCE.getGame(gameId);

        if (game.hasFreeSeat()) {
            game.setPlayerColorOf(player, color);
        } else {
            throw new Exception("This game is full");
        }

        GameStorage.INSTANCE.setGame(game);

        return new Game();
    }
}

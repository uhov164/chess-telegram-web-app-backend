package com.telegram.bot.chess.storage;

import java.util.HashMap;

import com.telegram.bot.chess.model.Game;

public enum GameStorage {

    INSTANCE(new HashMap<String, Game>());

    private HashMap<String, Game> games;

    GameStorage(HashMap<String, Game> games) {
        this.games = games;
    }

    public Game getGame(String gameId) {
        return games.get(gameId);
    }

    public void setGame(Game game) {
        var gameId = game.getGameId();
        games.put(gameId, game);
    }

}

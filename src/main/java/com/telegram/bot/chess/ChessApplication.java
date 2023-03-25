package com.telegram.bot.chess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.telegram.bot.chess.model.Color;
import com.telegram.bot.chess.model.Game;
import com.telegram.bot.chess.model.Player;
import com.telegram.bot.chess.storage.GameStorage;

@SpringBootApplication
public class ChessApplication {

	public static void main(String[] args) throws TelegramApiException {
		SpringApplication.run(ChessApplication.class, args);

		var game = new Game();
		var id = game.getGameId();
		
		Player p1 = new Player("p1", Color.WHITE);
		Player p2 = new Player("p2", Color.BLACK);

		game.setWhitePlayer(p1);
		game.setBlackPlayer(p2);

        GameStorage.INSTANCE.setGame(game);

		System.out.println(game);
	}
}

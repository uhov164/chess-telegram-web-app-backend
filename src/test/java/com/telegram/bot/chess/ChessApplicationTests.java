package com.telegram.bot.chess;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.telegram.bot.chess.model.Color;
import com.telegram.bot.chess.model.Game;
import com.telegram.bot.chess.model.Player;

@SpringBootTest
class ChessApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testGame() {
		var game = new Game();
		var id = game.getGameId();
		
		Player p1 = new Player("p1", Color.WHITE);
		Player p2 = new Player("p2", Color.BLACK);

		game.setWhitePlayer(p1);
		game.setBlackPlayer(p2);

		System.out.println(game);

	}

}

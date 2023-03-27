package com.telegram.bot.chess.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telegram.bot.chess.dto.GameStatusDTO;
import com.telegram.bot.chess.dto.GameStatusRequestDTO;
import com.telegram.bot.chess.dto.PossibleMovesRequestDTO;
import com.telegram.bot.chess.factory.GameStatusDTOFactory;
import com.telegram.bot.chess.dto.MakeMoveRequestDTO;
import com.telegram.bot.chess.storage.GameStorage;


@RestController
@CrossOrigin
@RequestMapping("/game")
public class GameController {

    @GetMapping("/getStatusGame")
    public ResponseEntity<GameStatusDTO> getCurrentData(@RequestBody GameStatusRequestDTO request) {
        var gameID = request.getGameID();
        var game = GameStorage.INSTANCE.getGame(gameID);
        var response = GameStatusDTOFactory.create(gameID, game.getField(), request.getPlayerLogin());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getPossibleMoves")
    public ResponseEntity<List<Integer[]>> getPossibleMoves(@RequestBody PossibleMovesRequestDTO request) {
        var game = GameStorage.INSTANCE.getGame(request.getGameId());

        var playerLogin = request.getPlayerLogin();
        var x = request.getX();
        var y = request.getY();

        var fieldOfMoves = game.getPossibleMoves(playerLogin, x, y);

        return ResponseEntity.ok(fieldOfMoves);
    }

    @PostMapping("/makeMove")
    public ResponseEntity<GameStatusDTO> makeMove(@RequestBody MakeMoveRequestDTO request) {
        var game = GameStorage.INSTANCE.getGame(request.getGameId());

        var playerLogin = request.getPlayerLogin();
        var oldX = request.getOldX();
        var oldY = request.getOldY();
        var newX = request.getNewX();
        var newY = request.getNewY();

        game.makeMove(playerLogin, oldX, oldY, newX, newY);

        var response = GameStatusDTOFactory.create(game.getGameId(), game.getField(), request.getPlayerLogin());
        return ResponseEntity.ok(response);
    }
}
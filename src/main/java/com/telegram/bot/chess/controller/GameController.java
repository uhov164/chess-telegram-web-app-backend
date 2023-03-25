package com.telegram.bot.chess.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telegram.bot.chess.dto.GameStatusDTO;
import com.telegram.bot.chess.dto.GetGameStatusRequest;
import com.telegram.bot.chess.dto.GetPossibleMovesRequest;
import com.telegram.bot.chess.dto.MakeMoveRequest;
import com.telegram.bot.chess.storage.GameStorage;


@RestController
@CrossOrigin
@RequestMapping("/game")
public class GameController {


    @PostMapping("/updateStatusGame")
    public ResponseEntity<GameStatusDTO> getCurrentData(@RequestBody GetGameStatusRequest request) {
        var gameID = request.getGameID();
        var game = GameStorage.INSTANCE.getGame(gameID);

        var response = new GameStatusDTO(gameID, game.getField(), request.getPlayerLogin());
        return ResponseEntity.ok(response);
    }


    @PostMapping("/getField")
    public ResponseEntity<GameStatusDTO> getField(@RequestBody GetGameStatusRequest request) {
        var gameId = request.getGameID();
        var game = GameStorage.INSTANCE.getGame(gameId);
        var response = new GameStatusDTO(game.getGameId(), game.getField(), request.getPlayerLogin());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/makeMove")
    public ResponseEntity<GameStatusDTO> makeMove(@RequestBody MakeMoveRequest request) {
        var game = GameStorage.INSTANCE.getGame(request.getGameId());

        var playerLogin = request.getPlayerLogin();
        var oldX = request.getOldX();
        var oldY = request.getOldY();
        var newX = request.getNewX();
        var newY = request.getNewY();

        game.makeMove(playerLogin, oldX, oldY, newX, newY);

        var response = new GameStatusDTO(game.getGameId(), game.getField(), request.getPlayerLogin());
        return ResponseEntity.ok(response);
    }
     
    @PostMapping("/getPossibleMoves")
    public ResponseEntity<List<Integer[]>> getPossibleMoves(@RequestBody GetPossibleMovesRequest request) {
        var game = GameStorage.INSTANCE.getGame(request.getGameId());

        var playerLogin = request.getPlayerLogin();
        var x = request.getX();
        var y = request.getY();

        var fieldOfMoves = game.getPossibleMoves(playerLogin, x, y);

        return ResponseEntity.ok(fieldOfMoves);
    }
}
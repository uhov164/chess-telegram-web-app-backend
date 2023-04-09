package com.telegram.bot.chess.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.telegram.bot.chess.dto.GameStatusDTO;
import com.telegram.bot.chess.dto.request.GameStatusRequestDTO;
import com.telegram.bot.chess.dto.request.MakeMoveRequestDTO;
import com.telegram.bot.chess.dto.request.PossibleMovesRequestDTO;
import com.telegram.bot.chess.factory.GameStatusDTOFactory;
import com.telegram.bot.chess.service.GameplayService;
import com.telegram.bot.chess.storage.GameStorage;

import lombok.RequiredArgsConstructor;


@RestController
@CrossOrigin
@RequiredArgsConstructor
public class GameController {

    private final GameplayService gameplayService;
    private final GameStatusDTOFactory gameStatusDTOFactory;

    private static final String GET_GAME_STATUS    = "/api/game/getGameStatus";
    private static final String GET_POSSIBLE_MOVES = "/api/game/possibleMoves";
    private static final String MAKE_MOVE          = "/api/game/makeMove";

    //GET не может иметь body, посмотреть как правильно сделать
    // -> сделать через RequesetParam
    @PostMapping(GET_GAME_STATUS)
    public ResponseEntity<GameStatusDTO> getGameStatus(@RequestBody GameStatusRequestDTO request) {
        var gameID   = request.getGameId();
        var game     = GameStorage.INSTANCE.getGame(gameID);

        System.out.println("GET_GAME_STATUS:");
        System.out.println(gameID + " " + request.getPlayerId());

        var response = gameStatusDTOFactory.createGameStatusDTO(game, request.getPlayerId());

        return ResponseEntity.ok(response);
    }


    @PostMapping(GET_POSSIBLE_MOVES)
    public ResponseEntity<List<List<Integer>>> getPossibleMoves(@RequestBody PossibleMovesRequestDTO request) {

        System.out.println(request);

        var playerLogin = request.getPlayerLogin();
        var gameId      = request.getGameId();
        var x = request.getX();
        var y = request.getY();

        var fieldOfMoves 
            = gameplayService.getPossibleMoves(playerLogin, gameId, x, y);

        return ResponseEntity.ok(fieldOfMoves);
    }

    @PostMapping(MAKE_MOVE)
    public ResponseEntity<GameStatusDTO> makeMove(@RequestBody MakeMoveRequestDTO request) {

        var playerLogin = request.getPlayerLogin();
        var gameId      = request.getGameId();
        var oldX        = request.getOldX();
        var oldY        = request.getOldY();
        var newX        = request.getNewX();
        var newY        = request.getNewY();

        gameplayService.makeMove(gameId, playerLogin, oldX, oldY, newX, newY);

        var game = GameStorage.INSTANCE.getGame(gameId);
        var response = gameStatusDTOFactory.createGameStatusDTO(game, playerLogin);
        return ResponseEntity.ok(response);
    }
}
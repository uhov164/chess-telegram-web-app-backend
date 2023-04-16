package com.telegram.bot.chess.factory;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.telegram.bot.chess.model.Color;
import com.telegram.bot.chess.model.Game;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GameFactory {

    private final FieldFactory fieldFactory;
    
    public Game createGame() {
        return new Game().builder()
                         .isKingMoved(false)
                         .isLeftRookMoved(false)
                         .isRightRookMoved(false)
                         .whoseMove(Color.WHITE)
                         .gameId(UUID.randomUUID().toString())
                         .field(fieldFactory.createField())
                         .build();
    }
}

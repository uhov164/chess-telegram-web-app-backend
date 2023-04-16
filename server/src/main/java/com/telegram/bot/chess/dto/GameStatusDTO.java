package com.telegram.bot.chess.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameStatusDTO {
    private String gameID;
    private List<List<Integer>> field;
    private int yourColor;
}

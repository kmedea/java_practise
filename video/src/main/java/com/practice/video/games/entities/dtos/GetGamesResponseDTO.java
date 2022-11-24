package com.practice.video.games.entities.dtos;

import com.practice.video.games.entities.models.Game;

import java.util.ArrayList;
import java.util.List;

public class GetGamesResponseDTO {

    List<GameDTO> games;

    public GetGamesResponseDTO() {
        this.games = new ArrayList<>();
    }

    public List<GameDTO> getGames() {
        return games;
    }

    public void setGames(List<GameDTO> games) {
        this.games = games;
    }
}

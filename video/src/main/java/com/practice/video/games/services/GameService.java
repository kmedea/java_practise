package com.practice.video.games.services;

import com.practice.video.categories.entities.dtos.CategoryDTO;
import com.practice.video.games.entities.dtos.CreateGameDTO;
import com.practice.video.games.entities.dtos.CreateGameResponseDTO;
import com.practice.video.games.entities.dtos.GameDTO;
import com.practice.video.games.entities.dtos.GetGamesResponseDTO;

import java.util.List;

public interface GameService {
   GetGamesResponseDTO getAllGames();

    CreateGameResponseDTO createNewGame(CreateGameDTO createGameDTO);

    GameDTO getGameById(Integer id);

    GameDTO changeCategoryById(Integer id, CategoryDTO category);

    List<GameDTO> getGameByKeyword(String keyword);

    Integer deleteGame(Integer id);
}

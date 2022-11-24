package com.practice.video.games.services;

import com.practice.video.games.entities.dtos.CreateGameDTO;
import com.practice.video.games.entities.dtos.CreateGameResponseDTO;
import com.practice.video.games.entities.dtos.GetGamesResponseDTO;

public interface GameService {
   GetGamesResponseDTO getAllGames();

    CreateGameResponseDTO createNewGame(CreateGameDTO createGameDTO);
}

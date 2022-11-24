package com.practice.video.games.controllers;

import com.practice.video.games.entities.dtos.CreateGameDTO;
import com.practice.video.games.entities.dtos.CreateGameResponseDTO;
import com.practice.video.games.entities.dtos.GetGamesResponseDTO;
import com.practice.video.games.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("api/games")
    public ResponseEntity<GetGamesResponseDTO> getAllGames(){
        return ResponseEntity.status(200).body(gameService.getAllGames());
    }

    @PostMapping("api/games")
    public ResponseEntity<CreateGameResponseDTO> createNewGame(@RequestBody @Valid CreateGameDTO createGameDTO){
        return ResponseEntity.status(201).body(gameService.createNewGame(createGameDTO));
    }
}

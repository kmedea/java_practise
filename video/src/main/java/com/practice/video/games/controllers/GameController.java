package com.practice.video.games.controllers;

import com.practice.video.categories.entities.dtos.CategoryDTO;
import com.practice.video.games.entities.dtos.CreateGameDTO;
import com.practice.video.games.entities.dtos.CreateGameResponseDTO;
import com.practice.video.games.entities.dtos.GameDTO;
import com.practice.video.games.entities.dtos.GetGamesResponseDTO;
import com.practice.video.games.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("api/games")
    public ResponseEntity<?> getGames(@RequestParam (required = false) String keyword) {
        if(keyword==null || keyword.trim().isEmpty()) {
            return ResponseEntity.status(200).body(gameService.getAllGames());
        } else {
            return ResponseEntity.status(200).body(gameService.getGameByKeyword(keyword));
        }
    }

    @GetMapping("api/games/{id}")
    public ResponseEntity<GameDTO> getGameById(@PathVariable(required = false) Integer id) {
        return ResponseEntity.status(200).body(gameService.getGameById(id));
    }

    @PostMapping("api/games")
    public ResponseEntity<CreateGameResponseDTO> createNewGame(@RequestBody @Valid CreateGameDTO createGameDTO) {
        return ResponseEntity.status(201).body(gameService.createNewGame(createGameDTO));
    }

    @PutMapping("api/games/{id}/category")
    public ResponseEntity<GameDTO> changeCategory(@PathVariable Integer id,
                                                  @RequestBody @Valid CategoryDTO category){
        return ResponseEntity.status(201).body(gameService.changeCategoryById(id, category));
    }

    @DeleteMapping("/api/games/{id}")
    public ResponseEntity<Integer> deleteGame(@PathVariable Integer id){
        return ResponseEntity.ok(gameService.deleteGame(id));
    }
}

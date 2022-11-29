package com.practice.video.games.services;

import com.practice.video.categories.entities.dtos.CategoryDTO;
import com.practice.video.categories.entities.models.Category;
import com.practice.video.categories.services.CategoryService;
import com.practice.video.exceptions.errorTypes.GameIsAlreadyExistException;
import com.practice.video.exceptions.errorTypes.GameNotFoundException;
import com.practice.video.games.entities.dtos.CreateGameDTO;
import com.practice.video.games.entities.dtos.CreateGameResponseDTO;
import com.practice.video.games.entities.dtos.GameDTO;
import com.practice.video.games.entities.dtos.GetGamesResponseDTO;
import com.practice.video.games.entities.models.Game;
import com.practice.video.games.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;
    private CategoryService categoryService;

    public GameServiceImpl(GameRepository gameRepository, CategoryService categoryService) {
        this.gameRepository = gameRepository;
        this.categoryService = categoryService;
    }

    @Override
    public GetGamesResponseDTO getAllGames() {
        GetGamesResponseDTO gameList = new GetGamesResponseDTO();
        List<Game> getAllGames = (List<Game>) gameRepository.findAll();
        gameList.setGames(getAllGames.stream()
                .map(this::convertGameToGameDTO)
                .collect(Collectors.toList()));
        return gameList;
    }

    @Override
    public CreateGameResponseDTO createNewGame(CreateGameDTO createGameDTO) {
        if(gameRepository.findByName(createGameDTO.getName())!=null) {
            throw new GameIsAlreadyExistException();
        }
        Category category = categoryService.findCategoryByName(createGameDTO.getCategory());
        Game game = new Game();
        game.setName(createGameDTO.getName());
        game.setCategory(category == null ? categoryService.saveNewCategory(createGameDTO.getCategory()) : category);
        game.setReleaseYear(createGameDTO.getReleaseYear());
        game.setFinished(createGameDTO.getFinished());
        gameRepository.save(game);
        CreateGameResponseDTO createGameResponseDTO = new CreateGameResponseDTO();
        createGameResponseDTO.setGameId(game.getId());
        return createGameResponseDTO;
    }

    @Override
    public GameDTO getGameById(Integer id) {
       return convertGameToGameDTO(gameRepository.findById(id)
                .orElseThrow(GameNotFoundException::new));
    }

    @Override
    public GameDTO changeCategoryById(Integer id, CategoryDTO categoryDTO) {
        Game game = gameRepository.findById(id).orElseThrow(GameNotFoundException::new);
        Category category = categoryService.findCategoryByName(categoryDTO.getCategory());
        game.setCategory(category == null ? categoryService.saveNewCategory(categoryDTO.getCategory()) : category);
        gameRepository.save(game);
        return convertGameToGameDTO(game);

    }

    @Override
    public List<GameDTO> getGameByKeyword(String keyword) {
       return getAllGames().getGames().stream()
                .filter(game -> game.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                        game.getCategoryName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Integer deleteGame(Integer id) {
        Game game = gameRepository.findById(id).orElseThrow(GameNotFoundException::new);
        gameRepository.deleteById(id);
        return game.getId();
    }

    private GameDTO convertGameToGameDTO (Game game){
        GameDTO gameDTO = new GameDTO();
        gameDTO.setId(game.getId());
        gameDTO.setName(game.getName());
        gameDTO.setReleaseYear(game.getReleaseYear());
        gameDTO.setCategoryName(game.getCategory().getName());
        gameDTO.setFinished(game.getFinished());

        return gameDTO;
    }
}

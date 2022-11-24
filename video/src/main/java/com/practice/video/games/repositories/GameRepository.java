package com.practice.video.games.repositories;

import com.practice.video.games.entities.models.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
public interface GameRepository extends CrudRepository<Game, Integer> {

    Game findByName(String name);
}

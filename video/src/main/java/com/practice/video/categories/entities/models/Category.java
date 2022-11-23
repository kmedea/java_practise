package com.practice.video.categories.entities.models;

import com.practice.video.games.entities.models.Game;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "category")
    private Set<Game> games;

    public Category() {
        this.games = new HashSet<>();
    }

    public Category(String name) {
        this();
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

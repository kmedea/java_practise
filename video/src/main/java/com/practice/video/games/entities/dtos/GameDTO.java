package com.practice.video.games.entities.dtos;

import com.practice.video.categories.entities.models.Category;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class GameDTO {

    private Integer id;
    private String name;
    private Integer releaseYear;
    private String categoryName;
    private Boolean finished;

    public GameDTO() {
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

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }
}

package com.practice.video.games.entities.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateGameDTO {
    @NotBlank(message = "The name field is required")
    private String name;
    @NotNull(message = "The releaseYear field is required")
    private Integer releaseYear;
    @NotBlank(message = "The category field is required")
    private String category;
    private boolean finished;

    public CreateGameDTO() {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }
}

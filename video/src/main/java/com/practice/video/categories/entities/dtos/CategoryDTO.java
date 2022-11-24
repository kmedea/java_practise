package com.practice.video.categories.entities.dtos;

import javax.validation.constraints.NotBlank;

public class CategoryDTO {

    @NotBlank(message = "The category field is required")
    private String category;

    public CategoryDTO() {
    }

    public CategoryDTO(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

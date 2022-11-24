package com.practice.video.categories.repositories;

import com.practice.video.categories.entities.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

    Category findCategoryByName(String categoryName);
}

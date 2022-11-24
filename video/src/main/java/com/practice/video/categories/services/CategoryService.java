package com.practice.video.categories.services;

import com.practice.video.categories.entities.models.Category;

public interface CategoryService {

   Category findCategoryByName (String categoryName);
   Category saveNewCategory (String categoryName);
}

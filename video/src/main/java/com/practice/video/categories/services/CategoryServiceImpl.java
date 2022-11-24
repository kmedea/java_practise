package com.practice.video.categories.services;

import com.practice.video.categories.entities.models.Category;
import com.practice.video.categories.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findCategoryByName(String categoryName) {
        return categoryRepository.findCategoryByName(categoryName);
    }

    @Override
    public Category saveNewCategory(String categoryName) {
        Category newCategory = new Category(categoryName);
        return categoryRepository.save(newCategory);
    }
}

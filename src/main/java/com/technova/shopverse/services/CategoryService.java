package com.technova.shopverse.services;

import com.technova.shopverse.dtos.CategoryDTO;
import com.technova.shopverse.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();
    Optional<Category> getCategoryById(Long id);
    Category createCategory(Category category);
    Category updateCategory(Long id, Category category);
    void deleteCategory(Long id);
    CategoryDTO getCategoryDTOById(Long id);
}




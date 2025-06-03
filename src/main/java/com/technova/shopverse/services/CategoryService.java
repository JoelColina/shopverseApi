package com.technova.shopverse.services;

import com.technova.shopverse.dtos.CategoryDTO;
import com.technova.shopverse.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(Long id);
    CategoryDTO createCategory(CategoryDTO category);
    CategoryDTO updateCategory(Long id, CategoryDTO category);
    void deleteCategory(Long id);
    CategoryDTO getCategoryDTOById(Long id);
}




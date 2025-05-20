package com.technova.shopverse.services.impl;

import com.technova.shopverse.dtos.CategoryDTO;
import com.technova.shopverse.models.Category;
import com.technova.shopverse.repository.CategoryRepository;
import com.technova.shopverse.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(category -> new CategoryDTO(category)).toList();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
//        category.setId(id);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
    public CategoryDTO getCategoryDTOById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
        List<String> productNames = category
                .getProducts()
                .stream()
                .map(product -> product.getName())
                .toList();
        return new CategoryDTO(category);
    }
}

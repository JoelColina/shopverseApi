package com.technova.shopverse.services.impl;

import com.technova.shopverse.dtos.CategoryDTO;
import com.technova.shopverse.dtos.ProductDTO;
import com.technova.shopverse.models.Category;
import com.technova.shopverse.models.Product;
import com.technova.shopverse.repository.CategoryRepository;
import com.technova.shopverse.services.CategoryService;
import jakarta.persistence.Id;
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
    public CategoryDTO getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(CategoryDTO::new)
                .orElseThrow(() -> (new IllegalArgumentException("Categoría no encontrada")));
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDto) {

        validate(categoryDto);

        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        return new CategoryDTO(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDto) {

        validate(categoryDto);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        return new CategoryDTO(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) {

        if (!categoryRepository.existsById(id)){
            throw new IllegalArgumentException("El Id de la categoría no existe");
        }

        categoryRepository.deleteById(id);
    }

    public CategoryDTO getCategoryDTOById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
        return new CategoryDTO(category);
    }

    public void validate(CategoryDTO categoryDto){
        if (categoryDto.getName()==null || categoryDto.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede ser nulo");
        }

        if (categoryDto.getDescription()==null || categoryDto.getDescription().isBlank()) {
            throw new IllegalArgumentException("La descripción de la categoría no puede ser nulo");
        }
    }
}

package com.technova.shopverse.controller;

import com.technova.shopverse.models.Category;
import com.technova.shopverse.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController{

    // Inyectamos el repositorio con @Autowired
    @Autowired
    private CategoryService categoryService;

    private List<Category> categories = new ArrayList<>();

    // GET /api/categories
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategorys() {
        List<Category> categorys = categoryService.getAllCategories();
        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.ok(categorys); // 200 OK
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategorysById(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/categories
    @PostMapping
    public Category createCategorys(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategorys(@PathVariable Long id, @RequestBody Category updatedCategory) {
        try {
            Category updated = categoryService.updateCategory(id, updatedCategory);
            return ResponseEntity.ok(updated); // 200 OK
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found si no existe
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deleteCategorys(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}

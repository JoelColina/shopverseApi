package com.technova.shopverse.controller;

import com.technova.shopverse.dtos.CategoryDTO;
import com.technova.shopverse.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Category-Controllers", description = "Operaciones relacionadas con las Categorias")
public class CategoryController{

    // Inyectamos el repositorio con @Autowired
    @Autowired
    private CategoryService categoryService;

    @Operation(
            summary = "Obtener todos las categorias",
            description = "Este endpoint devuelve una lista con todos las categorias disponibles"
    )
    @ApiResponse(responseCode = "200", description = "Lista las categorias retornada correctamente")
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategorys() {
        List<CategoryDTO> categoriesDto = categoryService.getAllCategories();
        if (categoriesDto.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.ok(categoriesDto); // 200 OK
        }
    }

    @Operation(
            summary = "Obtener categorias por Id",
            description = "Este endpoint devuelve una las categorias disponibles filtradas por Id"
    )
    @ApiResponse(responseCode = "200", description = "Busqueda de categorias retornada correctamente")
    @GetMapping("/{id}")
    public CategoryDTO getCategorysById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @Operation(
            summary = "Crea categorias",
            description = "Este endpoint se encarga de crear categorias"
    )
    @ApiResponse(responseCode = "200", description = "Categorias creada correctamente")
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategorys(@Valid @RequestBody CategoryDTO categoryDTO) {
       try{
           CategoryDTO created = categoryService.createCategory(categoryDTO);
           return ResponseEntity.status(201).body(created);
       } catch (IllegalArgumentException e) {
           return ResponseEntity.badRequest().build();
       }
    }
    @Operation(
            summary = "Actualiza categorias",
            description = "Este endpoint se encarga de actualizar las categorias disponibles"
    )
    @ApiResponse(responseCode = "200", description = "Categorias actualizada correctamente")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategorys(@Valid @PathVariable Long id, @RequestBody CategoryDTO updatedCategoryDTO) {
        try {
            CategoryDTO updated = categoryService.updateCategory(id, updatedCategoryDTO);
            return ResponseEntity.ok(updated); // 200 OK
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found si no existe
        }
    }
    @Operation(
            summary = "Elimina categorias",
            description = "Este endpoint se encarga de elimanr las categorias disponibles"
    )
    @ApiResponse(responseCode = "200", description = "Categorias eliminada correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorys(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
    @Operation(
            summary = "Cargar Categorias por archivo csv.",
            description = "Este endpoint se encarga de cargar las categorias por archivo csv."
    )
    @ApiResponse(responseCode = "200", description = "Categorias cargados correctamente")
    @GetMapping("/{id}/details")
    public ResponseEntity<CategoryDTO> getCategoryDetails(@PathVariable Long id) {
        try {
            CategoryDTO dto = categoryService.getCategoryDTOById(id);
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

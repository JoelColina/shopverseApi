package com.technova.shopverse.controller;

import com.technova.shopverse.dtos.ProductDTO;
import com.technova.shopverse.models.Product;
import com.technova.shopverse.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    // Inyectamos el repositorio con @Autowired
    @Autowired
    private ProductService productService;

     @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        List<ProductDTO> productsDto = productService.getAllProductDTOs();
        if (productsDto.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.ok(productsDto); // 200 OK
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductsById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createProducts(@Valid @RequestBody Product products) {
        try{
            productService.createProduct(products);
            return new ResponseEntity<>("Producto Creado correctamente", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProducts(@Valid @PathVariable Long id, @RequestBody Product updatedProducts) {
        try {
            Product updated = productService.updateProduct(id, updatedProducts);
            return ResponseEntity.ok(updated); // 200 OK
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found si no existe
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducts(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getByCategory(@PathVariable Long categoryId) {
        List<ProductDTO> products = productService.getByCategoryId(categoryId);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }
}


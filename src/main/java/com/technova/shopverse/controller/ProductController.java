package com.technova.shopverse.controller;

import com.technova.shopverse.dtos.ProductDTO;
import com.technova.shopverse.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product-Controllers", description = "Operaciones relacionadas con productos")
public class ProductController {

    // Inyectamos el repositorio con @Autowired
    @Autowired
    private ProductService productService;

    @Operation(
            summary = "Obtener todos los productos",
            description = "Este endpoint devuelve una lista con todos los productos disponibles"
    )
    @ApiResponse(responseCode = "200", description = "Lista de productos retornada correctamente")
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        List<ProductDTO> productsDto = productService.getAllProductDTOs();
        if (productsDto.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.ok(productsDto); // 200 OK
        }
    }
    @Operation(
            summary = "Obtener productos por Id",
            description = "Este endpoint devuelve el producto mediante el Id."
    )
    @ApiResponse(responseCode = "200", description = "Productos retornada correctamente")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductsById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Crea productos",
            description = "Este endpoint Se encarga de crear el producto."
    )
    @ApiResponse(responseCode = "200", description = "Productos creado correctamente")
    @PostMapping
    public ResponseEntity<String> createProducts(@Valid @RequestBody ProductDTO productsDto) {
        try{
            productService.createProduct(productsDto);
            return new ResponseEntity<>("Producto Creado correctamente", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

    }
    @Operation(
            summary = "Actualiza productos por Id",
            description = "Este endpoint Se encarga de actualizar los producto."
    )
    @ApiResponse(responseCode = "200", description = "Productos actualizado correctamente")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProducts(@Valid @PathVariable Long id, @RequestBody ProductDTO updatedProductsDto) {
        try {
            ProductDTO updated = productService.updateProduct(id, updatedProductsDto);
            return ResponseEntity.ok(updated); // 200 OK
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found si no existe
        }
    }
    @Operation(
            summary = "Elimina productos por Id",
            description = "Este endpoint Se encarga de eliminacion de producto."
    )
    @ApiResponse(responseCode = "200", description = "Productos eliminado correctamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducts(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
    @Operation(
            summary = "Cargar productos por archivo csv.",
            description = "Este endpoint se encarga de cargar productos por archivo csv."
    )
    @ApiResponse(responseCode = "200", description = "Productos cargados correctamente")
    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getByCategory(@PathVariable Long categoryId) {
        List<ProductDTO> products = productService.getByCategoryId(categoryId);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }
}


package com.technova.shopverse.services;

import com.technova.shopverse.dtos.ProductDTO;
import com.technova.shopverse.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDTO> getAllProducts();
    Optional<ProductDTO> getProductById(Long id);
    ProductDTO createProduct(ProductDTO product);
    ProductDTO updateProduct(Long id, ProductDTO product);
    void deleteProduct(Long id);
    public List<ProductDTO> getAllProductDTOs();
    List<ProductDTO> getByCategoryId(Long categoryId);
}


package com.technova.shopverse.services.impl;

import com.technova.shopverse.dtos.CategoryDTO;
import com.technova.shopverse.dtos.ProductDTO;
import com.technova.shopverse.models.Category;
import com.technova.shopverse.models.Product;
import com.technova.shopverse.repository.CategoryRepository;
import com.technova.shopverse.repository.ProductRepository;
import com.technova.shopverse.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(ProductDTO::new).toList();
    }

    @Override
    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id).map(ProductDTO::new);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {

        validateProduct(productDTO);

        Category category = categoryRepository.findByName(productDTO.getCategoryName())
                        .orElseThrow(() -> new IllegalArgumentException("No se encontro la Categoría con el nombre " + productDTO.getCategoryName()));

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());

        product.setCategory(category);

        return new ProductDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        validateProduct(productDTO);

        Product product = productRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("No se encontro el producto con el id " + id));

        Category category = categoryRepository.findByName(productDTO.getCategoryName()).
                orElseThrow(() -> new IllegalArgumentException("No se encontro la Categoría con el nombre " + productDTO.getCategoryName()));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);

        return new ProductDTO(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {

        if (!productRepository.existsById(id)){
            throw new IllegalArgumentException("El Id del producto no existe");
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> getAllProductDTOs() {
        return productRepository.findAll().stream().map(product -> new ProductDTO(product)).toList();
    }

    public ProductDTO toDTO(Product product) {
        String categoryName = product.getCategory() != null ? product.getCategory().getName() : null;
        return new ProductDTO(product);
            }
    public List<ProductDTO> getByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId).stream()
                .map(this::toDTO)
                .toList();
    }
    public void validateProduct(ProductDTO productDTO) {
        if (productDTO.getName() == null || productDTO.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacío.");
        }
        if (productDTO.getDescription() == null || productDTO.getDescription().isBlank()) {
            throw new IllegalArgumentException("La descripción del producto no puede ser nulo o vacío.");
        }
        if (productDTO.getPrice() == null || productDTO.getPrice() <= 0) {
            throw new IllegalArgumentException("El precio del producto no puede ser nulo.");
        }
    }
}

package com.technova.shopverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopverseApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopverseApiApplication.class, args);
	}


//	@Bean
//	public CommandLineRunner initData(ProductRepository productRepository,
//									  CategoryRepository categoryRepository) {
//		return args -> {
//
//		    Product product1 = new Product( "Laptop Lenovo", "Notebook 15 pulgadas", 850.0);
//			Product product2 = new Product( "Mouse Logitech", "Mouse inalámbrico", 25.5);
//			Product product3 = new Product( "Monitor Samsung", "Monitor 24 pulgadas", 199.99);
//			Product product4 = new Product("Laptop HP", "Notebook 17 pulgadas", 1000.0);
//
//			productRepository.save(product1);
//			productRepository.save(product2);
//			productRepository.save(product3);
//			productRepository.save(product4);
//
//			Category category1 = new Category("Tecnología", "Productos electrónicos y computación");
//			Category category2 = new Category("Hogar", "Artículos para el hogar y decoración");
//			Category category3 = new Category("Indumentaria", "Ropa y accesorios");
//
//			categoryRepository.save(category1);
//			categoryRepository.save(category2);
//			categoryRepository.save(category3);
//
//		};
//	}

}

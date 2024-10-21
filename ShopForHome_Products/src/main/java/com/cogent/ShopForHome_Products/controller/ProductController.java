package com.cogent.ShopForHome_Products.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cogent.ShopForHome_Products.model.Product;
import com.cogent.ShopForHome_Products.service.ProductService;

//not a bean
@RestController
public class ProductController {
	@Autowired
	ProductService productService;

	@PostMapping("/register")
	public ResponseEntity<Product> register(@RequestBody Product product) {
		Optional<Product> existingProduct = productService.findProductById(product.getProductId());
		if (!existingProduct.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		productService.saveProduct(product);
		return ResponseEntity.ok(existingProduct.get());

	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> productList = productService.getAllProducts();
		if (productList.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(productList);
	}

	@GetMapping("/products/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable int productId) {
		Optional<Product> existingProduct = productService.findProductById(productId);
		if (existingProduct.isPresent()) {
			return ResponseEntity.ok(existingProduct.get());
		}
		return ResponseEntity.notFound().build();
	}

}

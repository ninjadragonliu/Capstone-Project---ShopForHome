package com.cogent.ShopForHome_Products.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cogent.ShopForHome_Products.model.Product;
import com.cogent.ShopForHome_Products.service.ProductService;

//not a bean
@RestController
public class ProductController {
	@Autowired
	ProductService productService;

	@PostMapping("/products/register")
	public ResponseEntity<Product> register(@RequestBody Product product) {
		Optional<Product> existingProduct = productService.findProductById(product.getProductId());
		if (!existingProduct.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		productService.saveProduct(product);
		return ResponseEntity.ok(product);

	}

	@GetMapping("/products/{category}")
	public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category){
		List<Product> productList = productService.getAllProductsByCategory(category);
		if (productList.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(productList);
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

	@PutMapping("/products/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable int productId, @RequestBody Product product) {
		Optional<Product> existingProduct = productService.findProductById(productId);
		if (existingProduct.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		productService.updateProduct(productId, product);
		return ResponseEntity.ok(product);
	}

	@DeleteMapping("/products/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable int productId) {
		Optional<Product> existingProduct = productService.findProductById(productId);
		if (existingProduct.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		productService.deleteProduct(productId);
		return ResponseEntity.ok("Product deleted successfully");
	}
}

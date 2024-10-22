package com.cogent.ShopForHome_Products.contoller;


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
import org.springframework.web.service.annotation.GetExchange;

import com.cogent.ShopForHome_Products.model.Product;
import com.cogent.ShopForHome_Products.service.ProductService;


//not a bean
@RestController
public class ProductController {
	@Autowired
	private ProductService ProductService;


	@PostMapping("/products/register")
	public ResponseEntity<Product> register(@RequestBody Product product) {
		Optional<Product> op = ProductService.findProductById(product.getProductId());
		if (!op.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		ProductService.saveProduct(product);
		return ResponseEntity.ok(product);

	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts() {
		List <Product> products = ProductService.getAllProducts();
		if(products.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(products);
	}

	@GetExchange("/products/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable int productId) {
		Optional<Product> product = ProductService.findProductById(productId);
		if(product.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(product.get());
	}

	@PutMapping("/products/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable int productId, @RequestBody Product product) {
		Optional<Product> existingProduct = ProductService.findProductById(productId);
		if(existingProduct.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		ProductService.updateProduct(productId, product);
		return ResponseEntity.ok(product);
	}

	@DeleteMapping("/products/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable int productId) {
		Optional<Product> existingProduct = ProductService.findProductById(productId);
		if(existingProduct.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		ProductService.deleteProduct(productId);
		return ResponseEntity.ok("Product deleted successfully");
	}
}

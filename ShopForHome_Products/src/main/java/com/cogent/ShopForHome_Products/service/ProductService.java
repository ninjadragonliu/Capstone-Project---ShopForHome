package com.cogent.ShopForHome_Products.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cogent.ShopForHome_Products.model.Product;
import com.cogent.ShopForHome_Products.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	public Optional<Product> findProductById(int productId) {
		return productRepository.findById(productId);
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	public List<Product> getAllProductsByCategory(String category){
		List<Product> productList = getAllProducts();
		if(productList.isEmpty()) {
			return null;
		}
		List<Product> productsByCategory = new ArrayList<>();
		for(Product product: productList) {
			if(product.getCategory().equals(category)) {
				productsByCategory.add(product);
			}
		}
		return productsByCategory;
	}
	
	public Product updateProduct(int productId, Product product) {
		Optional<Product> existingProduct = productRepository.findById(product.getProductId());
		if (existingProduct.isEmpty()) {
			return null;
		}

		product.setProductId(productId);
		return productRepository.saveAndFlush(product);
		
	}

	public boolean deleteProduct(int productId) {
		Optional<Product> existingProduct = productRepository.findById(productId);
		if (existingProduct.isEmpty()) {
			return false;
		} else {
			productRepository.deleteById(productId);
			return true;
		}
	}
}

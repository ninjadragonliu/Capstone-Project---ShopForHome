package com.cogent.ShopForHome_Products.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cogent.ShopForHome_Products.model.Product;
import com.cogent.ShopForHome_Products.repository.ProductRepository;


@Service
public class ProductService{
	@Autowired
	ProductRepository productRepository;
	
	public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    
	public Optional<Product> findProductById(int productId) {
		return productRepository.findById(productId);
	}

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}

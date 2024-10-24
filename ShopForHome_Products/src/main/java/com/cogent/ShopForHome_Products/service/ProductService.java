package com.cogent.ShopForHome_Products.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
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
	
	
	public List<Product> bulkUpload(String filename) {
		List<String[]> data = new ArrayList<String[]>();
				String testRow;
				try (BufferedReader br = new BufferedReader(new FileReader(filename))){
					while ((testRow = br.readLine()) != null) {
						String[] line = testRow.split(",");
						data.add(line);
					}
				} catch (FileNotFoundException e) {
					System.out.println("ERROR: File not found " + filename);
				} catch (IOException e) {
					System.out.println("ERROR: Could not read " + filename);
				}
				
				System.out.println(filename);
				
				List<Product> productList = new ArrayList<Product>();
				
				for(String [] line: data) {
					Product newProduct = new Product();
					newProduct.setName(line[0]);
					newProduct.setDescription(line[1]);
					newProduct.setPrice(new BigDecimal(line[2]));
					newProduct.setStock(Integer.parseInt(line[3]));
					newProduct.setCategory(line[4]);
					
					productRepository.save(newProduct);
					productList.add(newProduct);
					
				}
				
				return productList;
			
	}
	
}

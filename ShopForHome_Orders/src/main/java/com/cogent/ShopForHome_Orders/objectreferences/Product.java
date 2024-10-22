package com.cogent.ShopForHome_Orders.objectreferences;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// reference object
public class Product {
	private int productId;
	private String name;
	private String description;
	private BigDecimal price;
	private int stock = 1;
	private String category;
	private LocalDateTime createdAt;

	public Product() {
		// default
	}

	public Product(String name, String description, BigDecimal price, String category) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
	}

	// getters&setters
	public int getProductId() {
		return productId;
	}

	public void setProduct_id(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreated_at(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}

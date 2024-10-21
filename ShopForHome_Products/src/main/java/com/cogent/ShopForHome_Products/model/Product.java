package com.cogent.ShopForHome_Products.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;

	@Column(nullable = false)
	private String name;
	private String description;

	@Column(nullable = false)
	private BigDecimal price;

	@Column(nullable = false)
	private int stock = 1;

	@Column(nullable = false)
	private String category;

	@CreationTimestamp
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

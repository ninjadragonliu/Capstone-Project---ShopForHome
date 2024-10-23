package com.cogent.ShopForHome_Carts.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cartitem")
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int itemId;

	@ManyToOne
	@JoinColumn(name = "cartId", nullable = false)
	private Cart cart;

	private int productId;
	private int quantity;
	private BigDecimal price;

	@CreationTimestamp
	private LocalDateTime createdAt;

	public CartItem() {
		// default
	}

	public CartItem(Cart cart, int productId, int quantity, BigDecimal price) {
		this.cart = cart;
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}

	// getters&setters
	public int getItemId() {
		return itemId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}

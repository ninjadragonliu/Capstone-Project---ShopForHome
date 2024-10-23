package com.cogent.ShopForHome_Users.objectreference;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CartItem {
	
	private int itemId;

	private Cart cart;

	private int productId;
	private int quantity;
	private BigDecimal price;

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

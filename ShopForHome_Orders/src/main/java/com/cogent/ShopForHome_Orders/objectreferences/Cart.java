package com.cogent.ShopForHome_Orders.objectreferences;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//reference object
public class Cart {
	
	private int cartId;
	private int userId;
	private List<CartItem> cartItems = new ArrayList<>();
	private LocalDateTime createdAt;

	public Cart() {
		// default
	}

	public Cart(int userId) {
		this.userId = userId;
	}

	public void clearCart() {
		this.cartItems.clear();
	}
	
	// getters&setters
	public int getCartId() {
		return cartId;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreated_at(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}

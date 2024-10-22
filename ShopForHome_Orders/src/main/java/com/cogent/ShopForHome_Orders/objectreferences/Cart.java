package com.cogent.ShopForHome_Orders.objectreferences;

import java.time.LocalDateTime;

//reference object
public class Cart {
	private int cartId;
	private User user;
	private LocalDateTime createdAt;

	public Cart() {
		// default
	}

	public Cart(User user) {
		this.user = user;
	}

	// getters&setters
	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreated_at(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}

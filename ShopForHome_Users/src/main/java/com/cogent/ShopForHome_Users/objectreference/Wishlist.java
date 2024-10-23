package com.cogent.ShopForHome_Users.objectreference;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



public class Wishlist {
	private int wishlistId;
	private int userId;
	private List<WishlistItem> wishlistItems = new ArrayList<>();
	private LocalDateTime createdAt;

	public Wishlist() {
		// default
	}

	public Wishlist(int userId) {
		this.userId = userId;
	}
	
	public void clearWishlist() {
		this.wishlistItems.clear();
	}

	// getters&setters
	public int getWishlistId() {
		return wishlistId;
	}

	public List<WishlistItem> getWishlistItems() {
		return wishlistItems;
	}

	public void setWishlistItems(List<WishlistItem> wishlistItems) {
		this.wishlistItems = wishlistItems;
	}

	public void setWishlistId(int wishlistId) {
		this.wishlistId = wishlistId;
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

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}

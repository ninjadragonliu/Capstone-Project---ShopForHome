package com.cogent.ShopForHome_Users.objectreference;

import java.time.LocalDateTime;


public class WishlistItem {

	private int itemId;
	private int productId;
	private Wishlist wishlist;
	private LocalDateTime createdAt;

	public WishlistItem() {
		// default
	}

	public WishlistItem(Wishlist wishlist, int productId) {
		this.wishlist = wishlist;
		this.productId = productId;
	}

	// getters&setters
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public Wishlist getWishlist() {
		return wishlist;
	}

	public void setWishlist(Wishlist wishlist) {
		this.wishlist = wishlist;
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
	
	
}

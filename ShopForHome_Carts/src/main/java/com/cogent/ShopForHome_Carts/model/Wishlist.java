package com.cogent.ShopForHome_Carts.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "wishlist")
public class Wishlist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int wishlistId;
	private int userId;

	@OneToMany(mappedBy = "wishlistId", cascade = CascadeType.REMOVE)
	private List<WishlistItem> wishlistItems = new ArrayList<>();
	
	@CreationTimestamp
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

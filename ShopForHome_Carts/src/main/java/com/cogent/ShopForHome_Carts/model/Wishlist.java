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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "wishlist")
public class Wishlist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int wishlistId;

	@OneToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;

	@OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL)
	private List<WishlistItem> wishlistItems = new ArrayList<>();
	
	@CreationTimestamp
	private LocalDateTime createdAt;

	public Wishlist() {
		// default
	}

	public Wishlist(User user) {
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}

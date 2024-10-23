package com.cogent.ShopForHome_Carts.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cogent.ShopForHome_Carts.model.Wishlist;
import com.cogent.ShopForHome_Carts.model.WishlistItem;
import com.cogent.ShopForHome_Carts.objectreference.Product;
import com.cogent.ShopForHome_Carts.objectreference.User;
import com.cogent.ShopForHome_Carts.repository.WishlistItemRepository;
import com.cogent.ShopForHome_Carts.repository.WishlistRepository;




@Service
public class WishlistService {
	@Autowired
	private WishlistRepository wishlistRepository;

	@Autowired
	private WishlistItemRepository wishlistItemRepository;

	public Optional<WishlistItem> findByWishlistItemId(int wishlistItemId) {
		return wishlistItemRepository.findById(wishlistItemId);
	}
	
	public Wishlist getWishlistByUser(int userId) {
		return wishlistRepository.findByUserId(userId).orElseGet(() -> {
			Wishlist newWishlist = new Wishlist(userId);
			return wishlistRepository.save(newWishlist);
		});
	}

	public WishlistItem addProductToWishlist(User user, Product product) {
		Wishlist wishlist = getWishlistByUser(user.getUserId());
		Optional<WishlistItem> existingWishlistItem = wishlistItemRepository.findByWishlistAndProductId(wishlist, product.getProductId());
		WishlistItem wishlistItem = new WishlistItem();
		if (existingWishlistItem.isEmpty()) {
			wishlistItem = new WishlistItem(wishlist, product.getProductId());
		} else {
			wishlistItem = existingWishlistItem.get();
		}

		return wishlistItemRepository.save(wishlistItem);
	}

	public boolean removeProductFromWishlist(User user, Product product) {
		Wishlist wishlist = getWishlistByUser(user.getUserId());
		Optional<WishlistItem> existingWishlistItem = wishlistItemRepository.findByWishlistAndProductId(wishlist, product.getProductId());
		if(existingWishlistItem.isEmpty()) {
			return false;
		} 
		WishlistItem found = existingWishlistItem.get();
		List<WishlistItem> wishlistItems = wishlist.getWishlistItems();
		if(wishlistItems.contains(found)) {
			wishlistItems.remove(found);
		}
		wishlistItemRepository.delete(found);
		return true;
	}

	public void clearWishlist(Wishlist wishlist) {
		List<WishlistItem> wishlistItems = wishlist.getWishlistItems();
		wishlistItemRepository.deleteAll(wishlistItems);
		wishlist.clearWishlist();
		wishlistRepository.save(wishlist);
	}
}

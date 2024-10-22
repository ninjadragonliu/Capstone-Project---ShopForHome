package com.cogent.ShopForHome_Carts.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cogent.ShopForHome_Carts.model.Product;
import com.cogent.ShopForHome_Carts.model.User;
import com.cogent.ShopForHome_Carts.model.Wishlist;
import com.cogent.ShopForHome_Carts.model.WishlistItem;
import com.cogent.ShopForHome_Carts.repository.WishlistItemRepository;
import com.cogent.ShopForHome_Carts.repository.WishlistRepository;




@Service
public class WishlistService {
	@Autowired
	private WishlistRepository wishlistRepository;

	@Autowired
	private WishlistItemRepository wishlistItemRepository;

	public Wishlist getWishlistByUser(User user) {
		return wishlistRepository.findByUser(user.getUserId()).orElseGet(() -> {
			Wishlist newWishlist = new Wishlist(user);
			return wishlistRepository.save(newWishlist);
		});
	}

	public WishlistItem addProductToWishlist(User user, Product product) {
		Wishlist wishlist = getWishlistByUser(user);
		Optional<WishlistItem> existingWishlistItem = wishlistItemRepository.findByWishlistAndProduct(wishlist, product);
		WishlistItem wishlistItem = new WishlistItem();
		if (existingWishlistItem.isEmpty()) {
			wishlistItem = new WishlistItem(wishlist, product);
		} else {
			wishlistItem = existingWishlistItem.get();
		}

		return wishlistItemRepository.save(wishlistItem);
	}

	public boolean removeProductFromWishlist(User user, Product product) {
		Wishlist wishlist = getWishlistByUser(user);
		Optional<WishlistItem> existingWishlistItem = wishlistItemRepository.findByWishlistAndProduct(wishlist, product);
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

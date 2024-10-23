package com.cogent.ShopForHome_Carts.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cogent.ShopForHome_Carts.model.Wishlist;
import com.cogent.ShopForHome_Carts.model.WishlistItem;
import com.cogent.ShopForHome_Carts.objectreference.Product;
import com.cogent.ShopForHome_Carts.objectreference.User;
import com.cogent.ShopForHome_Carts.service.WishlistService;
import com.cogent.ShopForHome_Carts.service.feign.ProductFeignClient;
import com.cogent.ShopForHome_Carts.service.feign.UserFeignClient;

@RestController
public class WishlistController{
	@Autowired
	private WishlistService wishlistService;
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	@Autowired
	private ProductFeignClient productFeignClient;
	
	
	@PostMapping("/wishlist/register")
	public ResponseEntity<Wishlist> wishlistRegister(@RequestBody Integer userId) {
		Wishlist existingWishlist = wishlistService.getWishlistByUser(userId);
		return ResponseEntity.ok().body(existingWishlist);
	}
	
	
	@PostMapping("/wishlist/{userId}/items")
	public ResponseEntity<List<WishlistItem>> addProductToWishlist(@PathVariable int userId, @RequestParam("productId") int productId) {
		Optional<User> existingUser = userFeignClient.getUserById(userId);
		if(existingUser.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Optional<Product> existingProduct = productFeignClient.getProductById(productId);
		if(existingProduct.isEmpty()) {
			return ResponseEntity.notFound().build(); 
		}
		User user = existingUser.get();
		Product product = existingProduct.get();
		wishlistService.addProductToWishlist(user, product);
		Wishlist wishlist = wishlistService.getWishlistByUser(user.getUserId());
		List<WishlistItem> wishlistItems = wishlist.getWishlistItems();
		return ResponseEntity.ok(wishlistItems);
	}
 
	@GetMapping("/wishlist/{userId}")
	public ResponseEntity<List<WishlistItem>> getWishlistByUser(@PathVariable int userId) {
		Optional<User> existingUser = userFeignClient.getUserById(userId);
		if(existingUser.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		User user = existingUser.get();
		Wishlist wishlist = wishlistService.getWishlistByUser(user.getUserId());
		List<WishlistItem> wishlistItems = wishlist.getWishlistItems();
		return ResponseEntity.ok(wishlistItems);
	}

	@DeleteMapping("wishlist/{userId}/items/{productId}")
	public ResponseEntity<List<WishlistItem>> removeProductFromWishlist(@PathVariable int userId, @PathVariable int productId){
		Optional<User> existingUser = userFeignClient.getUserById(userId);
		if(existingUser.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Optional<Product> existingProduct = productFeignClient.getProductById(productId);
		if(existingProduct.isEmpty()) {
			return ResponseEntity.notFound().build(); 
		}
		User user = existingUser.get();
		Product product = existingProduct.get();
		boolean removed = wishlistService.removeProductFromWishlist(user, product);
		if(!removed) {
			return ResponseEntity.notFound().build(); 
		}
		Wishlist wishlist = wishlistService.getWishlistByUser(user.getUserId());
		List<WishlistItem> wishlistItems = wishlist.getWishlistItems();
		return ResponseEntity.ok(wishlistItems);
	}
	
	@DeleteMapping("wishlist/{userId}/clear")
	public ResponseEntity<List<WishlistItem>> clearWishlist(@PathVariable int userId){
		Optional<User> existingUser = userFeignClient.getUserById(userId);
		if(existingUser.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		User user = existingUser.get();
		Wishlist wishlist = wishlistService.getWishlistByUser(user.getUserId());
		wishlistService.clearWishlist(wishlist);
		List<WishlistItem> wishlistItems = wishlist.getWishlistItems();
		return ResponseEntity.ok(wishlistItems);
		
		
	}
}


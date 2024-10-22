package com.cogent.ShopForHome_Carts.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cogent.ShopForHome_Carts.model.Cart;
import com.cogent.ShopForHome_Carts.model.CartItem;
import com.cogent.ShopForHome_Carts.model.Product;
import com.cogent.ShopForHome_Carts.model.User;
import com.cogent.ShopForHome_Carts.service.CartService;
import com.cogent.ShopForHome_Carts.service.ProductFeignClient;
import com.cogent.ShopForHome_Carts.service.UserFeignClient;


//not a bean
@RestController
public class CartsController {
	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	@Autowired
	private ProductFeignClient productFeignClient;
	
	@PostMapping("/cart/{userId}/register")
	public ResponseEntity<List<CartItem>> addProductToCart(@PathVariable int userId, @RequestParam("productId") int productId, @RequestParam("quantity") int quantity) {
		Optional<User> existingUser = userFeignClient.getUserById(userId);
		if(existingUser.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Optional<Product> existingProduct = productFeignClient.getProductById(productId);
		if(existingProduct.isEmpty()) {
			return ResponseEntity.notFound().build(); 
		}
		User user = existingUser.get();
		cartService.addProductToCart(user.getUserId(), existingProduct.get(), quantity);
		Cart cart = cartService.getCartByUser(user.getUserId());
		List<CartItem> cartItems = cart.getCartItems();
		return ResponseEntity.ok(cartItems);
	}
 
	@GetMapping("/cart/{userId}")
	public ResponseEntity<List<CartItem>> getCartByUser(@PathVariable int userId) {
		Optional<User> existingUser = userFeignClient.getUserById(userId);
		if(existingUser.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		User user = existingUser.get();
		Cart cart = cartService.getCartByUser(user.getUserId());
		List<CartItem> cartItems = cart.getCartItems();
		return ResponseEntity.ok(cartItems);
	}

	@DeleteMapping("cart/{userId}/{productId}")
	public ResponseEntity<List<CartItem>> removeProductFromCart(@PathVariable int userId, @PathVariable int productId){
		Optional<User> existingUser = userFeignClient.getUserById(userId);
		if(existingUser.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Optional<Product> existingProduct = productFeignClient.getProductById(productId);
		if(existingProduct.isEmpty()) {
			return ResponseEntity.notFound().build(); 
		}
		User user = existingUser.get();
		boolean removed = cartService.removeProductFromCart(user.getUserId(), existingProduct.get());
		if(!removed) {
			return ResponseEntity.notFound().build(); 
		}
		Cart cart = cartService.getCartByUser(user.getUserId());
		List<CartItem> cartItems = cart.getCartItems();
		return ResponseEntity.ok(cartItems);
	}
}

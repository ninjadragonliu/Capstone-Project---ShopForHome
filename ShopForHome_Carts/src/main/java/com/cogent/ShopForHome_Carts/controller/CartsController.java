package com.cogent.ShopForHome_Carts.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cogent.ShopForHome_Carts.model.Cart;
import com.cogent.ShopForHome_Carts.model.CartItem;
import com.cogent.ShopForHome_Carts.objectreference.ItemRequest;
import com.cogent.ShopForHome_Carts.objectreference.Product;
import com.cogent.ShopForHome_Carts.objectreference.User;
import com.cogent.ShopForHome_Carts.service.CartService;
import com.cogent.ShopForHome_Carts.service.feign.ProductFeignClient;
import com.cogent.ShopForHome_Carts.service.feign.UserFeignClient;


//not a bean
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CartsController {
	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	@Autowired
	private ProductFeignClient productFeignClient;
	

	@PostMapping("/cart/register")
	public ResponseEntity<Cart> cartRegister(@RequestBody int userId) {
		Cart existingCart = cartService.getCartByUser(userId);
		return ResponseEntity.ok(existingCart);
	}
	
	@PostMapping("/cart/{userId}/items")
	public ResponseEntity<List<CartItem>> addProductToCart(@PathVariable int userId, @RequestBody ItemRequest itemRequest) {
		Optional<User> existingUser = userFeignClient.getUserById(userId);
		if(existingUser.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Optional<Product> existingProduct = productFeignClient.getProductById(itemRequest.getProductId());
		if(existingProduct.isEmpty()) {
			return ResponseEntity.notFound().build(); 
		}
		User user = existingUser.get();
		cartService.addProductToCart(user.getUserId(), existingProduct.get(), itemRequest.getQuantity());
		Cart cart = cartService.getCartByUser(user.getUserId());
		List<CartItem> cartItems = cart.getCartItems();
		return ResponseEntity.ok(cartItems);
	}

	@DeleteMapping("cart/{userId}/clear")
	public ResponseEntity<List<CartItem>> clearCart(@PathVariable int userId){
		Optional<User> existingUser = userFeignClient.getUserById(userId);
		if(existingUser.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		User user = existingUser.get();

		Cart cart = cartService.getCartByUser(user.getUserId());
		cartService.clearCart(cart);
		List<CartItem> cartItems = cart.getCartItems();
		return ResponseEntity.ok(cartItems);
	}

	@GetMapping("/cart/{userId}")
	public ResponseEntity<Cart> getCartByUser(@PathVariable int userId) {
		Optional<User> existingUser = userFeignClient.getUserById(userId);
		if(existingUser.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		User user = existingUser.get();
		Cart cart = cartService.getCartByUser(user.getUserId());
		return ResponseEntity.ok(cart);
	}

	@DeleteMapping("/cart/{userId}/items/{cartItemId}")
	public ResponseEntity<List<CartItem>> removeProductFromCart(@PathVariable int userId, @PathVariable int cartItemId){
		Optional<User> existingUser = userFeignClient.getUserById(userId);
		if(existingUser.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Optional<CartItem> existingCartItem = cartService.findByCartItemId(cartItemId);
		if(existingCartItem.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		CartItem cartItem = existingCartItem.get();
		
		Optional<Product> existingProduct = productFeignClient.getProductById(cartItem.getProductId());
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
	


	
	@PatchMapping("/cart/{userId}/items/{cartItemId}")
	public ResponseEntity<CartItem> updateCartItemQuantity(@PathVariable int userId, @PathVariable int cartItemId, @RequestBody int quantity){
		Optional<User> existingUser = userFeignClient.getUserById(userId);
		if(existingUser.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Optional<CartItem> existingCartItem = cartService.findByCartItemId(cartItemId);
		if(existingCartItem.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		CartItem cartItem = existingCartItem.get();
		
		Optional<Product> existingProduct = productFeignClient.getProductById(cartItem.getProductId());
		if(existingProduct.isEmpty()) {
			return ResponseEntity.notFound().build(); 
		}
			
		CartItem updatedCartItem = cartService.updateCartItemQuantity(userId, cartItemId, quantity);
		return ResponseEntity.ok(updatedCartItem);
	}
}


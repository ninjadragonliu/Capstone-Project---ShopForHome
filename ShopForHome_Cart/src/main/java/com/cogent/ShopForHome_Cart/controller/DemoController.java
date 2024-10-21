package com.cogent.ShopForHome_Cart.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cogent.ShopForHome_Cart.service.CartItemService;
import com.cogent.ShopForHome_Cart.service.CartService;
import com.cogent.ShopForHome_Cart.service.WishlistItemService;
import com.cogent.ShopForHome_Cart.service.WishlistService;

import java.util.List;
import java.util.Optional;
import com.cogent.ShopForHome_Cart.model.Cart;


//not a bean
@RestController
public class DemoController {
	@Autowired
	CartService cartService;
	@Autowired
	CartItemService cartItemService;
	@Autowired
	WishlistService wishlistService;
	@Autowired
	WishlistItemService wishlistItemService;
	
	
	@GetMapping("/hi")
	public String hi() {
		return "hi.";
	}
	
	@GetMapping("/carts")
	public List<Cart> getCarts() {
		return cartService.getAllCarts();
	}
	
	@PostMapping("/cart")
	public ResponseEntity<Cart> saveCart(@RequestBody Cart cart) {
		Optional<Cart> op = cartService.findCartById(cart.getId());
		if (!op.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		cartService.saveCart(cart);
		return ResponseEntity.ok(cart);
	}

}

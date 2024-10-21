package com.cogent.ShopForHome_Cart.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cogent.ShopForHome_Cart.service.CartItemService;
import com.cogent.ShopForHome_Cart.service.CartService;
import com.cogent.ShopForHome_Cart.service.WishlistItemService;
import com.cogent.ShopForHome_Cart.service.WishlistService;




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
	
	
	

}

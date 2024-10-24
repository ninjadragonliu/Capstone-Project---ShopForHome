package com.cogent.ShopForHome_Orders.feign;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cogent.ShopForHome_Orders.objectreferences.Cart;
import com.cogent.ShopForHome_Orders.objectreferences.CartItem;

@Service
@FeignClient(name="ShopForHomeCarts", url = "http://localhost:9003/")
public interface CartsFeignClient {
	@GetMapping("/cart/{userId}")
	public Optional<Cart> getCartByUser(@PathVariable int userId);
	
	@DeleteMapping("cart/{userId}/clear")
	public Optional<List<CartItem>> clearCart(@PathVariable int userId);
}

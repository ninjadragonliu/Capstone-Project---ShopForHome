package com.cogent.ShopForHome_Users.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cogent.ShopForHome_Users.objectreference.Cart;
import com.cogent.ShopForHome_Users.objectreference.Wishlist;

@Service
@FeignClient(name="ShopForHomeCarts", url = "http://localhost:9003/")
public interface CartsFeignClient {

	@PostMapping("/cart/register")
	public ResponseEntity<Cart> cartRegister(@RequestBody Integer userId);
	
	@PostMapping("/wishlist/register")
	public ResponseEntity<Wishlist> wishlistRegister(@RequestBody Integer userId); 
	
}

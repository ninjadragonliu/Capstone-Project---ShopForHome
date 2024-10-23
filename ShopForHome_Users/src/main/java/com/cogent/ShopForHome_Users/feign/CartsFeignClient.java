package com.cogent.ShopForHome_Users.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cogent.ShopForHome_Users.objectreference.Cart;

@Service
@FeignClient(name="ShopForHomeCarts", url = "http://localhost:9003/")
public interface CartsFeignClient {

	@PostMapping("/cart/register")
	public String register(@RequestBody Cart cart);
}

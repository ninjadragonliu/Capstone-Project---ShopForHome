package com.cogent.ShopForHome_Orders.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@FeignClient(name="ShopForHomeUsers", url = "http://localhost:9001/")
public interface UsersFeignClient {

	@GetMapping("/users/test")
	public String hi();
}

package com.cogent.ShopForHome_Carts.service.feign;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cogent.ShopForHome_Carts.objectreference.User;

@Service
@FeignClient(name="ShopForHomeUsers", url = "http://localhost:9001/users")
public interface UserFeignClient {
	@GetMapping("/{userId}")
	public Optional<User> getUserById(@PathVariable int userId);
}

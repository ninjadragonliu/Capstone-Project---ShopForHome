package com.cogent.ShopForHome_Carts.service;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cogent.ShopForHome_Carts.model.User;

@Service
@FeignClient(name="UserService", url = "http://localhost:9001/users")
public interface UserFeignClient {
	@GetMapping("/{userId}")
	public Optional<User> getUserById(@PathVariable int userId);
}


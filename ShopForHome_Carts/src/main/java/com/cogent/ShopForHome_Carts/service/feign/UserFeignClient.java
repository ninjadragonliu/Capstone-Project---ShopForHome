package com.cogent.ShopForHome_Carts.service.feign;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cogent.ShopForHome_Carts.objectreference.User;

@Service
@FeignClient(name="ShopForHomeUsers", url = "http://localhost:9001/")
public interface UserFeignClient {
	@GetMapping("/users/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable int userId);
}


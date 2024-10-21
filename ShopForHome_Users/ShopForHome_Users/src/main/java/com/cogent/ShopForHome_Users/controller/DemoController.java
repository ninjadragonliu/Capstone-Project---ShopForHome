package com.cogent.ShopForHome_Users.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cogent.ShopForHome_Users.model.User;
import com.cogent.ShopForHome_Users.service.UserService;

//not a bean
@RestController
public class DemoController {
	@Autowired
	UserService userService;

	@GetMapping("/hi")
	public String hi() {
		return "hi.";
	}

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) {
		Optional<User> op = userService.findByUserId(user.getUserId());
		if (!op.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		userService.saveUser(user);
		return ResponseEntity.ok(user);

	}

}

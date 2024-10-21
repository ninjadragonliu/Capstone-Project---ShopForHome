package com.cogent.ShopForHome_Users.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.cogent.ShopForHome_Users.model.User;
import com.cogent.ShopForHome_Users.service.UserService;

//not a bean
@RestController
public class UserController {
	@Autowired
	UserService userService;


	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) {
		Optional<User> op = userService.findUserById(user.getUserId());
		if (!op.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		userService.saveUser(user);
		return ResponseEntity.ok(user);

	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		List <User> users = userService.getAllUsers();
		if(users.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(users);
	}

	@GetExchange("/users/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable int userId) {
		Optional<User> user = userService.findUserById(userId);
		if(user.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user.get());
	}

	@PutMapping("/users/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody User user) {
		Optional<User> existingUser = userService.findUserById(userId);
		if(existingUser.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		user.setUserId(userId);
		userService.updateUser(user);
		return ResponseEntity.ok(user);
	}

	@DeleteMapping("/users/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId) {
		Optional<User> existingUser = userService.findUserById(userId);
		if(existingUser.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		userService.deleteUser(userId);
		return ResponseEntity.ok("User deleted successfully");
	}

}

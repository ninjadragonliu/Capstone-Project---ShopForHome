package com.cogent.ShopForHome_Users.controller;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cogent.ShopForHome_Users.model.User;
import com.cogent.ShopForHome_Users.objectreference.LoginRequest;
import com.cogent.ShopForHome_Users.service.UserService;

import feign.Response;

//not a bean
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/users/login")
	public ResponseEntity<User> loginUser(@RequestBody LoginRequest loginRequest){
		Optional<User> op = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
		if (!op.isEmpty()) {
			return ResponseEntity.ok(op.get());
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping("/users/register")
	public ResponseEntity<User> register(@RequestBody User user) {
		Optional<User> op = userService.findUserById(user.getUserId());
		if (!op.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		userService.saveUser(user);
		return ResponseEntity.ok(user);

	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable int userId) {
		Optional<User> user = userService.findUserById(userId);
		if(user.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user.get());
	}
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}
	@PutMapping("/users/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody User user) {
		Optional<User> existingUser = userService.findUserById(userId);
		if(existingUser.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		userService.updateUser(userId, user);
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

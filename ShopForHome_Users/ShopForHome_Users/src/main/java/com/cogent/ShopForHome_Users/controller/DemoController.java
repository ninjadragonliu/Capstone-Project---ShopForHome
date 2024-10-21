package com.cogent.ShopForHome_Users.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cogent.ShopForHome_Users.service.UserService;




//not a bean
@RestController
public class DemoController {
	@Autowired
	UserService userService;
	
	
	@GetMapping("/hi")
	public String hi() {
		return "hi";
	}
	
	
	

}

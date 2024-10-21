package com.cogent.ShopForHome_Products.contoller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cogent.ShopForHome_Products.service.ProductService;


//not a bean
@RestController
public class DemoController {
	@Autowired
	ProductService productService;


	@GetMapping(value="/hi")
	public String hi(){
		return "hi.";
	}

}

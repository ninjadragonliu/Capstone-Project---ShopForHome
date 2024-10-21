package com.cogent.ShopForHome_Orders.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cogent.ShopForHome_Orders.service.OrderItemService;
import com.cogent.ShopForHome_Orders.service.OrderService;


//not a bean
@RestController
public class DemoController {
	@Autowired
	OrderService orderService;
	OrderItemService orderItemService;


	@GetMapping(value="/hi")
	public String hi(){
		return "hi.";
	}

}

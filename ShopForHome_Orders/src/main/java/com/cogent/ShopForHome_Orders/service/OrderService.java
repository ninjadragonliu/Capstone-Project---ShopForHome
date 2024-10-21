package com.cogent.ShopForHome_Orders.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cogent.ShopForHome_Orders.respository.OrderRepository;




@Service
public class OrderService{
	@Autowired
	OrderRepository orderRepository;
	
	
}

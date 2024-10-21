package com.cogent.ShopForHome_Orders.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cogent.ShopForHome_Orders.respository.OrderItemRepository;





@Service
public class OrderItemService{
	@Autowired
	OrderItemRepository orderItemRepository;
	
	
}

package com.cogent.ShopForHome_Carts.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cogent.ShopForHome_Carts.repository.CartItemRepository;




@Service
public class CartItemService {
	@Autowired
	CartItemRepository cartItemRepository;
	
	
}

package com.cogent.ShopForHome_Cart.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cogent.ShopForHome_Cart.repository.WishlistRepository;



@Service
public class WishlistService {
	@Autowired
	WishlistRepository wishlistRepository;
	
	
}

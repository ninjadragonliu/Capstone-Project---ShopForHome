package com.cogent.ShopForHome_Cart.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cogent.ShopForHome_Cart.model.Cart;
import com.cogent.ShopForHome_Cart.repository.CartRepository;



@Service
public class CartService {
	@Autowired
	CartRepository cartRepository;
	
	public void saveCart(Cart cart) {
		cartRepository.save(cart);
	}
	public Optional<Cart> findCartById(int cartId) {
		return cartRepository.findById(cartId);
	}

    public List<Cart> getAllCarts() {
        // TODO Auto-generated method stub
        return cartRepository.findAll();
    }
}

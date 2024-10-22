package com.cogent.ShopForHome_Carts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cogent.ShopForHome_Carts.model.Cart;
import com.cogent.ShopForHome_Carts.model.CartItem;
import com.cogent.ShopForHome_Carts.model.Product;
import com.cogent.ShopForHome_Carts.model.User;
import com.cogent.ShopForHome_Carts.repository.CartItemRepository;
import com.cogent.ShopForHome_Carts.repository.CartRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	public Cart getCartByUser(User user) {
		return cartRepository.findByUser(user.getUserId()).orElseGet(() -> {
			Cart newCart = new Cart(user);
			return cartRepository.save(newCart);
		});
	}

	public CartItem addProductToCart(User user, Product product, int quantity) {
		Cart cart = getCartByUser(user);
		Optional<CartItem> existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);
		CartItem cartItem = new CartItem();
		if (existingCartItem.isEmpty()) {
			cartItem = new CartItem(cart, product, quantity);
		} else {
			cartItem = existingCartItem.get();
			cartItem.setQuantity(cartItem.getQuantity() + quantity);
		}

		return cartItemRepository.save(cartItem);
	}

	public boolean removeProductFromCart(User user, Product product) {
		Cart cart = getCartByUser(user);
		Optional<CartItem> existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);
		if(existingCartItem.isEmpty()) {
			return false;
		} 
		CartItem found = existingCartItem.get();
		List<CartItem> cartItems = cart.getCartItems();
		if(cartItems.contains(found)) {
			cartItems.remove(found);
		}
		cartItemRepository.delete(found);
		return true;
	}

	public void clearCart(Cart cart) {
		List<CartItem> cartItems = cart.getCartItems();
		cartItemRepository.deleteAll(cartItems);
		cart.clearCart();
		cartRepository.save(cart);
	}
}

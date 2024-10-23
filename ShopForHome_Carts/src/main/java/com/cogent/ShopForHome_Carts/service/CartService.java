package com.cogent.ShopForHome_Carts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cogent.ShopForHome_Carts.model.Cart;
import com.cogent.ShopForHome_Carts.model.CartItem;
import com.cogent.ShopForHome_Carts.objectreference.Product;
import com.cogent.ShopForHome_Carts.repository.CartItemRepository;
import com.cogent.ShopForHome_Carts.repository.CartRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	public Optional<CartItem> findByCartItemId(int cartItemId) {
		return cartItemRepository.findById(cartItemId);
	}
	
	public CartItem updateCartItemQuantity(int userId, int cartItemId, int quantity) {
		CartItem cartItem = cartItemRepository.findById(cartItemId).get();
		cartItem.setQuantity(cartItem.getQuantity() + quantity);
		return cartItem;
	}
	
	public Cart getCartByUser(Integer userId) {
		return cartRepository.findByUserId(userId).orElseGet(() -> {
			Cart newCart = new Cart(userId);
			return cartRepository.save(newCart);
		});
	}

	public CartItem addProductToCart(Integer userId, Product product, Integer quantity) {
		Cart cart = getCartByUser(userId);
		Optional<CartItem> existingCartItem = cartItemRepository.findByCartIdAndProductId(cart.getCartId(), product.getProductId());
		CartItem cartItem = new CartItem();
		if (existingCartItem.isEmpty()) {
			cartItem = new CartItem(cart, product.getProductId(), quantity, product.getPrice());
		} else {
			cartItem = existingCartItem.get();
			cartItem.setQuantity(cartItem.getQuantity() + quantity);
		}

		return cartItemRepository.save(cartItem);
	}

	public boolean removeProductFromCart(int userId, Product product) {
		Cart cart = getCartByUser(userId);
		Optional<CartItem> existingCartItem = cartItemRepository.findByCartIdAndProductId(cart.getCartId(), product.getProductId());
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

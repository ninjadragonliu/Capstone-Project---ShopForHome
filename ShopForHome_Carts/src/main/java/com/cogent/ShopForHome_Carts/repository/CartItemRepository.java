package com.cogent.ShopForHome_Carts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cogent.ShopForHome_Carts.model.Cart;
import com.cogent.ShopForHome_Carts.model.CartItem;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer>{
   public Optional<CartItem> findByCartAndProductId(Cart cart, int productId);

}

package com.cogent.ShopForHome_Carts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cogent.ShopForHome_Carts.model.Cart;
import com.cogent.ShopForHome_Carts.model.CartItem;
import com.cogent.ShopForHome_Carts.model.Product;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer>{
   public Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

}

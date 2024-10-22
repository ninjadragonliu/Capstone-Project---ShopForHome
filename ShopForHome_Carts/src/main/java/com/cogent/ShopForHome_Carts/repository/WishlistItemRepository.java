package com.cogent.ShopForHome_Carts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cogent.ShopForHome_Carts.model.Product;
import com.cogent.ShopForHome_Carts.model.Wishlist;
import com.cogent.ShopForHome_Carts.model.WishlistItem;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem,Integer>{
	public Optional<WishlistItem> findByWishlistAndProduct(Wishlist wishlist, Product product);

}

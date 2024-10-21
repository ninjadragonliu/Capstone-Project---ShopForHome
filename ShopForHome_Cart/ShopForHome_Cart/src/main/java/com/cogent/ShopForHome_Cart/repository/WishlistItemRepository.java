package com.cogent.ShopForHome_Cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cogent.ShopForHome_Cart.model.WishlistItem;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem,Integer>{
   

}

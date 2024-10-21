package com.cogent.ShopForHome_Carts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cogent.ShopForHome_Carts.model.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,Integer>{
   

}

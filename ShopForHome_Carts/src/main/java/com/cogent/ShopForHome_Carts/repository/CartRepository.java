package com.cogent.ShopForHome_Carts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cogent.ShopForHome_Carts.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer>{
   

}
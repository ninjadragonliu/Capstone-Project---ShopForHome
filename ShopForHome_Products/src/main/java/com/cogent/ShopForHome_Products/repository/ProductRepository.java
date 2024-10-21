package com.cogent.ShopForHome_Products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cogent.ShopForHome_Products.model.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{
	   

}
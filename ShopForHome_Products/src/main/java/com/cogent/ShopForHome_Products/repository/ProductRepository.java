package com.cogent.ShopForHome_Products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cogent.ShopForHome_Products.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}

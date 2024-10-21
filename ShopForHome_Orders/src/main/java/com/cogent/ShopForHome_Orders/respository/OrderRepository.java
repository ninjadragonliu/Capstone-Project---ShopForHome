package com.cogent.ShopForHome_Orders.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cogent.ShopForHome_Orders.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}


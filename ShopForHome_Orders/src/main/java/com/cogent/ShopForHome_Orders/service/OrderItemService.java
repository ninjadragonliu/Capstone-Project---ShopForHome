package com.cogent.ShopForHome_Orders.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cogent.ShopForHome_Orders.model.OrderItem;
import com.cogent.ShopForHome_Orders.respository.OrderItemRepository;





@Service
public class OrderItemService{
	@Autowired
	OrderItemRepository orderItemRepository;

	public OrderItem saveOrderItem(OrderItem orderItem) {
		return orderItemRepository.save(orderItem);
	}
	public List<OrderItem> getAllOrderItems() {
		return orderItemRepository.findAll();
	}
	
	public Optional<OrderItem> findOrderItemById(int id) {
		return orderItemRepository.findById(id);
	}

	public boolean deleteOrderItem(int id) {
		Optional<OrderItem> existingOrderItem = orderItemRepository.findById(id);
		if (existingOrderItem.isEmpty()) {
			return false;
		} else {
			orderItemRepository.deleteById(id);
			return true;
		}
	}

	public OrderItem updateOrderItem(int id, OrderItem orderItem) {
		Optional<OrderItem> existingOrderItem = orderItemRepository.findById(id);
		if (existingOrderItem.isEmpty()) {
			return null;
		}
		orderItem.setItemId(id);
		return orderItemRepository.saveAndFlush(orderItem);
	}

}

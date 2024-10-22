package com.cogent.ShopForHome_Orders.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cogent.ShopForHome_Orders.model.Order;
import com.cogent.ShopForHome_Orders.respository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}

	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public Optional<Order> findOrderById(int orderId) {
		return orderRepository.findById(orderId);
	}

	public boolean deleteOrder(int orderId) {
		Optional<Order> existingOrder = orderRepository.findById(orderId);
		if (existingOrder.isEmpty()) {
			return false;
		} else {
			orderRepository.deleteById(orderId);
			return true;
		}
	}

	public Order updateOrder(int orderId, Order order) {
		Optional<Order> existingOrder = orderRepository.findById(order.getOrderId());
		if (existingOrder.isEmpty()) {
			return null;
		}
		order.setOrderId(orderId);
		return orderRepository.saveAndFlush(order);
	}

}

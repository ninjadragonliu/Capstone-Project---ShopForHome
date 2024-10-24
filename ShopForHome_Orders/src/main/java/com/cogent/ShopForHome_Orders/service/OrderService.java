package com.cogent.ShopForHome_Orders.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.ShopForHome.enums.OrderStatus;
import com.cogent.ShopForHome_Orders.model.Order;
import com.cogent.ShopForHome_Orders.model.OrderItem;
import com.cogent.ShopForHome_Orders.objectreferences.Cart;
import com.cogent.ShopForHome_Orders.objectreferences.CartItem;
import com.cogent.ShopForHome_Orders.respository.OrderItemRepository;
import com.cogent.ShopForHome_Orders.respository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public Order saveOrder(Cart cart) {
		Order order = new Order(cart.getUserId(), cart.getCartId());
		BigDecimal total = BigDecimal.ZERO;
		order.setTotal(total);
		orderRepository.save(order);
		for(CartItem cartItem: cart.getCartItems()) {
			OrderItem orderItem = new OrderItem(order.getOrderId(), cartItem.getProductId(), cartItem.getQuantity(), cartItem.getPrice());
			order.getOrderItems().add(orderItem);
			orderItemRepository.save(orderItem);
			total = total.add(cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
		}
		order.setTotal(total);
		order.setStatus(OrderStatus.PENDING);
		return orderRepository.saveAndFlush(order);
	}

	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public Optional<Order> findOrderById(int orderId) {
		return orderRepository.findById(orderId);
	}
	
	public Order completeOrder(Order order) {
		if(order.getStatus().equals(OrderStatus.PENDING)) {
			order.setStatus(OrderStatus.COMPLETED);
			orderRepository.saveAndFlush(order);
			return order;
		}
		return order;
	}

	public boolean cancelOrder(Order order) {
		if(order.getStatus().equals(OrderStatus.PENDING)) {
			order.setStatus(OrderStatus.CANCELLED);
			orderRepository.saveAndFlush(order);
			return true;
		}
		return false;
	}
	
	public boolean deleteOrder(Order order) {
		for (OrderItem orderItem: order.getOrderItems()) {
			orderItemRepository.delete(orderItem);
		}
		orderRepository.delete(order);
		return true;
	}

	public Order updateOrder(Order order, OrderItem updatedItem) {
		for(OrderItem orderItem: order.getOrderItems()) {
			if(orderItem.getProductId() == updatedItem.getProductId()) {
				orderItem = updatedItem;
				orderItemRepository.saveAndFlush(orderItem);
			}
		}
		return orderRepository.saveAndFlush(order);
	}

}

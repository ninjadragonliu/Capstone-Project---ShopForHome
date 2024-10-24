package com.cogent.ShopForHome_Orders.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.ShopForHome.enums.OrderStatus;
import com.cogent.ShopForHome_Orders.model.Order;
import com.cogent.ShopForHome_Orders.model.OrderItem;
import com.cogent.ShopForHome_Orders.objectreferences.Cart;
import com.cogent.ShopForHome_Orders.objectreferences.CartItem;
import com.cogent.ShopForHome_Orders.respository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	public Order saveOrder(Cart cart) {
		Order order = new Order(cart.getUserId(), cart.getCartId());
		BigDecimal total = BigDecimal.ZERO;
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		order.setTotal(total);
		order.setStatus(OrderStatus.PENDING);
		order.setOrderItems(orderItems);
		orderRepository.save(order);
		for(CartItem cartItem: cart.getCartItems()) {
			
			OrderItem orderItem = new OrderItem(order.getOrderId(), cartItem.getProductId(), cartItem.getQuantity(), cartItem.getPrice());	
			System.out.println(orderItem.getOrderId());
			orderItems.add(orderItem);
			total = total.add(cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
		}
		for(OrderItem item: orderItems) {
			item.getQuantity();
		}
		
		cart.clearCart();
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
			return order;
		}
		return order;
	}

	public boolean cancelOrder(Order order) {
		if(order.getStatus().equals(OrderStatus.PENDING)) {
			order.setStatus(OrderStatus.CANCELLED);
			return true;
		}
		return false;
	}
	
	public boolean deleteOrder(Order order) {
		orderRepository.delete(order);
		return true;
	}

	public Order updateOrder(Order order, OrderItem updatedItem) {
		for(OrderItem orderItem: order.getOrderItems()) {
			if(orderItem.getProductId() == updatedItem.getProductId()) {
				orderItem = updatedItem;
			}
		}
		return orderRepository.saveAndFlush(order);
	}

}

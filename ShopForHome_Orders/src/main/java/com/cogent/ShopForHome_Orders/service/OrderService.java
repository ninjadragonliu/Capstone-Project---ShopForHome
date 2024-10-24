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
		List<Order> currentOrder = orderRepository.findAll();
		Order order = null;
		
		
//		possible feign call in future
		for(Order item: currentOrder) {
			if(item.getUserId() == cart.getUserId()){
				order = item;
				break;
			}
		}
		if(order == null) {
			order = new Order(cart.getUserId(), cart.getCartId());
			orderRepository.save(order);
		}
		BigDecimal total = BigDecimal.ZERO;
		List<OrderItem> orderItems = new ArrayList<OrderItem>();

		
		List<OrderItem> currentOrderItems = order.getOrderItems();
		for(CartItem cartItem: cart.getCartItems()) {
			OrderItem orderItem = null;
			boolean itemCurrent = false;
//			performance issue
			for(OrderItem item: currentOrderItems) {
				if(item.getProductId() == cartItem.getProductId()){
					orderItem = item;
					orderItem.setQuantity(orderItem.getQuantity()+ item.getQuantity());
					itemCurrent = true;
					break;
				}
				
			}
			if(!itemCurrent){
				orderItem = new OrderItem(order.getOrderId(), cartItem.getProductId(), cartItem.getQuantity(), cartItem.getPrice());
			}
			orderItems.add(orderItem);
			total = total.add(cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
		}		
		cart.clearCart();
		order.setTotal(order.getTotal().add(total));
		order.setOrderItems(orderItems);
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

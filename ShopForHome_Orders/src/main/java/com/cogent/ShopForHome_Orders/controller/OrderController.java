package com.cogent.ShopForHome_Orders.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.ShopForHome.enums.OrderStatus;
import com.cogent.ShopForHome_Orders.feign.CartsFeignClient;
import com.cogent.ShopForHome_Orders.model.Order;
import com.cogent.ShopForHome_Orders.model.OrderItem;
import com.cogent.ShopForHome_Orders.objectreferences.Cart;
import com.cogent.ShopForHome_Orders.service.OrderService;

//not a bean
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@Autowired
	private CartsFeignClient cartsFeignClient;

	// Orders
	@PostMapping("/orders/{userId}/register")
	public ResponseEntity<Order> register(@PathVariable int userId) {
		Optional<Cart> existingCart = cartsFeignClient.getCartByUser(userId);
		if (existingCart.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Cart cart = existingCart.get();
		Order order = orderService.saveOrder(cart);
		cartsFeignClient.clearCart(userId);
		return ResponseEntity.ok(order);
	}

	@GetMapping("/orders/{orderId}/complete")
	public ResponseEntity<Order> completeOrder(@PathVariable int orderId) {
		Optional<Order> existingOrder = orderService.findOrderById(orderId);
		if (existingOrder.isPresent()) {
			Order order = orderService.completeOrder(existingOrder.get());
			if (order.getStatus().equals(OrderStatus.COMPLETED)) {
				return ResponseEntity.ok(order);
			}
		}
		return ResponseEntity.notFound().build();

	}

	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getOrders() {
		List<Order> orderList = orderService.getAllOrders();
		if (orderList.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(orderList);
	}

	@GetMapping("/orders/{orderId}")
	public ResponseEntity<Order> getOrderById(@PathVariable int orderId) {
		Optional<Order> existingOrder = orderService.findOrderById(orderId);
		if (existingOrder.isPresent()) {
			return ResponseEntity.ok(existingOrder.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/orders/{orderId}")
	public ResponseEntity<Order> updateOrder(@PathVariable int orderId, @RequestBody OrderItem orderItem) {
		Optional<Order> existingOrder = orderService.findOrderById(orderId);
		if (existingOrder.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Order order = orderService.updateOrder(existingOrder.get(), orderItem);
		return ResponseEntity.ok(order);
	}

	@DeleteMapping("/orders/{orderId}")
	public ResponseEntity<String> cancelOrder(@PathVariable int orderId) {
		Optional<Order> existingOrder = orderService.findOrderById(orderId);
		if (existingOrder.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		orderService.cancelOrder(existingOrder.get());
		return ResponseEntity.ok("Order cancelled successfully");
	}

	@DeleteMapping("/orders/{orderId}/delete")
	public ResponseEntity<String> deleteOrder(@PathVariable int orderId) {
		Optional<Order> existingOrder = orderService.findOrderById(orderId);
		if (existingOrder.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		orderService.deleteOrder(existingOrder.get());
		return ResponseEntity.ok("Order deleted successfully");
	}
}
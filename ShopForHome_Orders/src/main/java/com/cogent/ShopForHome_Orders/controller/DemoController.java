package com.cogent.ShopForHome_Orders.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cogent.ShopForHome_Orders.model.Order;
import com.cogent.ShopForHome_Orders.service.OrderItemService;
import com.cogent.ShopForHome_Orders.service.OrderService;


//not a bean
@RestController
public class DemoController {
	@Autowired
	OrderService orderService;
	OrderItemService orderItemService;


	@PostMapping("/register")
	public ResponseEntity<Order> register(@RequestBody Order order) {
		Optional<Order> existingOrder = orderService.findOrderById(order.getOrderId());
		if (!existingOrder.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		orderService.saveOrder(order);
		return ResponseEntity.ok(order);
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
	public ResponseEntity<Order> updateOrder(@PathVariable int orderId, @RequestBody Order order) {
		Optional<Order> existingOrder = orderService.findOrderById(orderId);
		if (existingOrder.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		orderService.updateOrder(orderId, order);
		return ResponseEntity.ok(order);
	}

	@DeleteMapping("/orders/{orderId}")
	public ResponseEntity<String> deleteOrder(@PathVariable int orderId) {
		Optional<Order> existingOrder = orderService.findOrderById(orderId);
		if (existingOrder.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		orderService.deleteOrder(orderId);
		return ResponseEntity.ok("Order deleted successfully");
	}

}

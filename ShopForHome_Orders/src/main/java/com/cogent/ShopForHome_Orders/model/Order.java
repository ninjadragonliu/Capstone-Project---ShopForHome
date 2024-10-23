package com.cogent.ShopForHome_Orders.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.capstone.ShopForHome.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order1")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	private int userId;
	private int cartId;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();
	
	@Column(nullable = false)
	private BigDecimal total;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@CreationTimestamp
	private LocalDateTime createdAt;

	public Order() {
		// default
	}

	public Order(int userId, int cartId) {
		this.userId = userId;
		this.cartId = cartId;
	}

	// getters&setters
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	public Cart getCart() {
//		return cart;
//	}
//
//	public void setCart(Cart cart) {
//		this.cart = cart;
//	}

	
	public int getUserId() {
		return userId;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}

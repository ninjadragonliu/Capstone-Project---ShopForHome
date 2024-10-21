package com.cogent.ShopForHome_Products.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cogent.ShopForHome_Products.repository.ProductRepository;




@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	
}

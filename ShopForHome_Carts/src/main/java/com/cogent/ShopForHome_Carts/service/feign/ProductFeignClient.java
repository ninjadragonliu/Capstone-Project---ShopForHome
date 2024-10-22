package com.cogent.ShopForHome_Carts.service.feign;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cogent.ShopForHome_Carts.objectreference.Product;

@Service
@FeignClient(name="ShopForHomeProducts", url = "http://localhost:9005/products")
public interface ProductFeignClient {
	@GetMapping("/{productId}")
	public Optional<Product> getProductById(@PathVariable int productId);
}

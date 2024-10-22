package com.cogent.ShopForHome_Orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ShopForHomeOrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopForHomeOrdersApplication.class, args);
	}

}

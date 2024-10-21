package com.cogent.ShopForHome_Users.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cogent.ShopForHome_Users.repository.UserRepository;


@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	
}

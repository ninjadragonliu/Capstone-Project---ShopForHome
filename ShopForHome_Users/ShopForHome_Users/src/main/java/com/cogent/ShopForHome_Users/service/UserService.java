package com.cogent.ShopForHome_Users.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.cogent.ShopForHome_Users.model.User;
import com.cogent.ShopForHome_Users.repository.UserRepository;


@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public User saveUser(User user) {
        return userRepository.save(user);
    }
    
	public Optional<User> findUserById(int userId) {
		return userRepository.findById(userId);
	}

    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        return userRepository.findAll();
    }
}

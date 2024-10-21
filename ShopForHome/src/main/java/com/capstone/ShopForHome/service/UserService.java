package com.capstone.ShopForHome.service;

import org.springframework.stereotype.Service;

import com.capstone.ShopForHome.models.User;
import com.capstone.ShopForHome.repository.UserRepository;


@Service
public class UserService {

    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }
    
    public User findUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }
}

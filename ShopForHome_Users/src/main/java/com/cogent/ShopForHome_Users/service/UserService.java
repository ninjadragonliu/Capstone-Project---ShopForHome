package com.cogent.ShopForHome_Users.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cogent.ShopForHome_Users.feign.CartsFeignClient;
import com.cogent.ShopForHome_Users.model.User;
import com.cogent.ShopForHome_Users.repository.UserRepository;


@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartsFeignClient cartsFeignClient;
	
	public User saveUser(User user) {
//		New user added to table	
        User createdUser = userRepository.save(user);
//      Initialize new cart  
        cartsFeignClient.cartRegister(createdUser.getUserId());
        cartsFeignClient.wishlistRegister(createdUser.getUserId());    
        return createdUser;    
    }
    
	public Optional<User> findUserById(int userId) {
		return userRepository.findById(userId);
	}

    public void deleteUserById(int userId) {

        userRepository.deleteById(userId);
    }

    public User updateUser(int userId, User user) {
       Optional<User> existingUser = userRepository.findById(user.getUserId());
       if(existingUser.isEmpty()) {
           return null;
       }
       user.setUserId(userId);
       return userRepository.saveAndFlush(user);
    }

    public boolean deleteUser(int userId) {
        Optional<User> existingUser = userRepository.findById(userId);
        if(existingUser.isEmpty()) {
            return false;
        }
        else{
            userRepository.deleteById(userId);
            return true;
        }
    }
}

package com.cogent.ShopForHome_Users.repository;

import com.cogent.ShopForHome_Users.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUsername(String username);
}

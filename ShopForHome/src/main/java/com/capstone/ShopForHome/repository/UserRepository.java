package com.capstone.ShopForHome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.ShopForHome.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}

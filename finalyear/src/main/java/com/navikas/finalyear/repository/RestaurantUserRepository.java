package com.navikas.finalyear.repository;

import com.navikas.finalyear.entities.RestaurantUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantUserRepository extends JpaRepository<RestaurantUser, String> {
    RestaurantUser findByRestaurantName(String restaurantName);
}

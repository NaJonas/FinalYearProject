package com.navikas.finalyear.repository;

import com.navikas.finalyear.entities.RestaurantUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantUserRepository extends JpaRepository<RestaurantUser, String> {
    RestaurantUser findByRestaurantName(String restaurantName);
    List<RestaurantUser> findAllByRestaurantNameContainsIgnoreCase(String searchTerm);
}

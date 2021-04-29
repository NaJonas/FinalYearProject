package com.navikas.finalyear.repository;

import com.navikas.finalyear.entities.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository <MenuItem, Long> {
    List<MenuItem> findAllByRestaurantEmail(String restaurantEmail);
    List<MenuItem> findAllByRestaurantRestaurantName(String restaurantName);
}

package com.navikas.finalyear.repository;

import com.navikas.finalyear.entities.RestaurantUser;
import com.navikas.finalyear.entities.Tables;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

public interface TableRepository extends JpaRepository<Tables, Long> {
    List<Tables> findAllByRestaurantEmail(String restaurantEmail);
    Tables findByIdAndRestaurantEmail(Long id, String restaurantEmail);
    List<Tables> findByRestaurantEmailAndIsAvailable(String restaurantEmail, Boolean isAvailable);
}

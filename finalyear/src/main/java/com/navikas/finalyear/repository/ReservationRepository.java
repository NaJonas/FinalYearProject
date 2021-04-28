package com.navikas.finalyear.repository;

import com.navikas.finalyear.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByRestaurantEmail(String restaurantEmail);
    List<Reservation> findAllByCustomerEmail(String customerEmail);
}

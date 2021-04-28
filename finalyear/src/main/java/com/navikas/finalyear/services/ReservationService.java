package com.navikas.finalyear.services;

import com.navikas.finalyear.entities.Reservation;

public interface ReservationService {
    boolean saveReservation(Reservation reservation, String restaurantEmail, String customerEmail);
}

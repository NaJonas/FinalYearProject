package com.navikas.finalyear.services;

import com.navikas.finalyear.additionalclasses.TempReservation;
import com.navikas.finalyear.entities.CustomerUser;
import com.navikas.finalyear.entities.RestaurantUser;

import java.util.List;

public interface CustomerProfileService{
    List<TempReservation> getCustomerReservations(String customerEmail);
    CustomerUser findByEmail(String email);
    void deleteReseration(Long id);

}

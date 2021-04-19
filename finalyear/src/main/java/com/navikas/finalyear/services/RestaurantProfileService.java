package com.navikas.finalyear.services;

import com.navikas.finalyear.entities.RestaurantUser;

import java.util.List;

public interface RestaurantProfileService {
    void saveProfile(RestaurantUser restaurantUser, String email);
    RestaurantUser findByEmail(String email);
     List<RestaurantUser> findAll();
}

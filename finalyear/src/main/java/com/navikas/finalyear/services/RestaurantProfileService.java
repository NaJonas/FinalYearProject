package com.navikas.finalyear.services;

import com.navikas.finalyear.additionalclasses.TempReservation;
import com.navikas.finalyear.entities.MenuItem;
import com.navikas.finalyear.entities.RestaurantUser;
import com.navikas.finalyear.entities.Tables;

import java.util.List;

public interface RestaurantProfileService {
    void saveProfile(RestaurantUser restaurantUser, String email);
    RestaurantUser findByEmail(String email);
    List<RestaurantUser> findAll();
    List<Tables> getTables(String restaurantEmail);
    void updateTable(Tables table, String restaurantEmail);
    void saveTable(Tables table, RestaurantUser restaurantUser);
    List<TempReservation> getReservationsForProfile(String restaurantEmail);
    void addMenuItem(String restaurantEmail, MenuItem menuItem);
    List<MenuItem> getMenuItems(String restaurantEmail);
    List<MenuItem> getMenuItemsByRestaurantName(String restaurantName);
    List<RestaurantUser> getAllBySearch(String restaurantName);
}
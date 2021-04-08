package com.navikas.finalyear.services;

import com.navikas.finalyear.entities.CustomerUser;
import com.navikas.finalyear.entities.RestaurantUser;
import com.navikas.finalyear.entities.User;

public interface UserService {
    void registerCustomerUser(CustomerUser user);
    void registerRestaurantUser(RestaurantUser user);
    Boolean alreadyExist(String email);
}

package com.navikas.finalyear.services;

import com.navikas.finalyear.entities.RestaurantUser;
import com.navikas.finalyear.repository.RestaurantUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantProfileServiceImpl implements RestaurantProfileService{
    private RestaurantUserRepository restaurantUserRepository;

    @Autowired
    public RestaurantProfileServiceImpl(RestaurantUserRepository restaurantUserRepository){
        this.restaurantUserRepository = restaurantUserRepository;
    }

    @Override
    public void saveProfile(RestaurantUser restaurantUser, String email){
        RestaurantUser user = restaurantUserRepository.findById(email).orElse(null);
        user.setAddress(restaurantUser.getAddress());
        user.setRestaurantName(restaurantUser.getRestaurantName());
        user.setDescription(restaurantUser.getDescription());
        restaurantUserRepository.save(user);
    }
    @Override
    public RestaurantUser findByEmail(String email){
        return restaurantUserRepository.findById(email).orElse(null);
    }
    @Override
    public List<RestaurantUser> findAll(){
        return restaurantUserRepository.findAll();
    }
}

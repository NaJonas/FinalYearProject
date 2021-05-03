package com.navikas.finalyear.services;

import com.navikas.finalyear.entities.CustomerUser;
import com.navikas.finalyear.entities.RestaurantUser;
import com.navikas.finalyear.repository.CustomerUserRepository;
import com.navikas.finalyear.repository.RestaurantUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private CustomerUserRepository customerUserRepository;
    private RestaurantUserRepository restaurantUserRepository;

    @Autowired
    public UserServiceImpl (CustomerUserRepository customerUserRepository, RestaurantUserRepository restaurantUserRepository){
        this.customerUserRepository = customerUserRepository;
        this.restaurantUserRepository = restaurantUserRepository;
    }

    @Override
    public void registerCustomerUser(CustomerUser user) {
        customerUserRepository.save(user);
    }
    @Override
    public void registerRestaurantUser(RestaurantUser user){
        // Default authorization - false
        user.setIsAuthorized(false);
        restaurantUserRepository.save(user);
    }
    @Override
    public Boolean alreadyExist(String email){
        return customerUserRepository.findById(email).isPresent() || restaurantUserRepository.findById(email).isPresent();

    }
}

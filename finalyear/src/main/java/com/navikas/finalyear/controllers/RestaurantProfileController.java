package com.navikas.finalyear.controllers;

import com.navikas.finalyear.entities.CustomerUser;
import com.navikas.finalyear.entities.RestaurantUser;
import com.navikas.finalyear.services.RestaurantProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class RestaurantProfileController {
    RestaurantProfileService restaurantProfileService;
    //---------------------------------------------------------------------------------------
    // Restaurant profile edit for restaurant users
    public RestaurantProfileController(RestaurantProfileService restaurantProfileService) {
        this.restaurantProfileService = restaurantProfileService;
    }

    @RequestMapping(value = "/restaurantProfile", method = RequestMethod.GET)
    public ModelAndView showRestaurantProfile(Principal principal){
        // Get the logged in restaurant entity and show it on the web page
        RestaurantUser restaurantUser = restaurantProfileService.findByEmail(principal.getName());
        return new ModelAndView("restaurantProfile", "editProfile", restaurantUser);

    }

    @RequestMapping(value="/restaurantProfile", method = RequestMethod.POST)
    public String editProfile(RestaurantUser restaurantUser, Principal principal){
        restaurantProfileService.saveProfile(restaurantUser, principal.getName());
        return "redirect:/restaurantProfile";
    }

}

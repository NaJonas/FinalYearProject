package com.navikas.finalyear.controllers;

import com.navikas.finalyear.entities.CustomerUser;
import com.navikas.finalyear.entities.RestaurantUser;
import com.navikas.finalyear.entities.Tables;
import com.navikas.finalyear.services.RestaurantProfileService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value="/tableManagement", method = RequestMethod.GET)
    public ModelAndView manageTables(Principal principal){
        //RestaurantUser restaurantUser = restaurantProfileService.findByEmail(principal.getName());
        return new ModelAndView("tableManagement");
    }

    @RequestMapping(value="/tableManagement", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String saveTables(@RequestParam("left") int left, @RequestParam("top") int top, Principal principal){
        System.out.println(left);
        System.out.println(top);
        //RestaurantUser restaurantUser = restaurantProfileService.findByEmail(principal.getName());
        return "success";
    }

}

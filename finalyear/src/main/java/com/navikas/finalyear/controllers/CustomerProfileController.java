package com.navikas.finalyear.controllers;

import com.navikas.finalyear.additionalclasses.TempReservation;
import com.navikas.finalyear.entities.CustomerUser;
import com.navikas.finalyear.entities.RestaurantUser;
import com.navikas.finalyear.services.CustomerProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class CustomerProfileController {
    private CustomerProfileService customerProfileService;

    public CustomerProfileController(CustomerProfileService customerProfileService) {
        this.customerProfileService = customerProfileService;
    }

    @RequestMapping(value = "/customerProfile", method = RequestMethod.GET)
    public ModelAndView showRestaurantProfile(Principal principal){
        // Get the logged in customer entity
        CustomerUser customerUser = customerProfileService.findByEmail(principal.getName());
        List<TempReservation> reservations = customerProfileService.getCustomerReservations(principal.getName());
        for (TempReservation tmp : reservations){
            System.out.println(tmp.getId());

        }
        ModelAndView modelAndView = new ModelAndView("customerProfile", "reservations", reservations);
        return modelAndView;
    }

    @RequestMapping(value="/customerProfile", method = RequestMethod.POST)
    public String cancelReservation(Principal principal, TempReservation reservation){
        customerProfileService.deleteReseration(reservation.getId());
        return "redirect:/customerProfile";
    }
}

package com.navikas.finalyear.controllers;

import com.navikas.finalyear.additionalclasses.TempReservation;
import com.navikas.finalyear.entities.CustomerUser;
import com.navikas.finalyear.entities.RestaurantUser;
import com.navikas.finalyear.services.CustomerProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

/*
    Customer profile controller for all functionality for the customer user

 */

@Controller
public class CustomerProfileController {
    private CustomerProfileService customerProfileService;

    public CustomerProfileController(CustomerProfileService customerProfileService) {
        this.customerProfileService = customerProfileService;
    }

    // Get method for the customer profile page
    @RequestMapping(value = "/customerProfile", method = RequestMethod.GET)
    public ModelAndView showCustomerProfile(Principal principal){
        // Get the logged in customer entity
        CustomerUser customerUser = customerProfileService.findByEmail(principal.getName());
        List<TempReservation> reservations = customerProfileService.getCustomerReservations(principal.getName());

        ModelAndView modelAndView = new ModelAndView("customerProfile", "reservations", reservations);
        return modelAndView;
    }

    // Post method for customer profile that cancels a selected reservation
    @RequestMapping(value="/customerProfile", method = RequestMethod.POST)
    public String cancelReservation(Principal principal, TempReservation reservation){
        customerProfileService.deleteReseration(reservation.getId());
        return "redirect:/customerProfile";
    }
    // Post method for customer profile that changes a password.
    @RequestMapping(value="/changePassword", method = RequestMethod.POST)
    public String changePassword(Principal principal, String password, RedirectAttributes redirectAttributes){
        customerProfileService.changePassword(principal.getName(), password);
        redirectAttributes.addFlashAttribute("success", "Password has been changed successfully");
        return "redirect:/customerProfile";
    }
    // Post method for customer profile that deletes the account.
    @RequestMapping(value="/deleteAccount", method = RequestMethod.POST)
    public String deleteUser(Principal principal){
        customerProfileService.deleteUser(principal.getName());
        return "redirect:/";
    }
}

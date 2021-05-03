package com.navikas.finalyear.controllers;

import com.navikas.finalyear.entities.Reservation;
import com.navikas.finalyear.entities.RestaurantUser;
import com.navikas.finalyear.entities.Tables;
import com.navikas.finalyear.services.ReservationService;
import com.navikas.finalyear.services.RestaurantProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Map;

/*
    Controller to process reservation
 */
@Controller
public class ReservationController {
    RestaurantProfileService restaurantProfileService;
    ReservationService reservationService;

    public ReservationController(RestaurantProfileService restaurantProfileService, ReservationService reservationService) {
        this.restaurantProfileService = restaurantProfileService;
        this.reservationService = reservationService;
    }

    // Reservation controller method, that calls reservations service where the algorithm is. Based on a response, make a reservation or show error message
    @PostMapping
    @RequestMapping(value="/restaurantInfo/{restaurantName}", method = RequestMethod.POST)
    public String addReservation(Reservation reservation, Model model, @RequestParam Map<String,String> allParams, @RequestParam(value = "email")String restaurantEmail, Principal principal, RedirectAttributes redirectAttributes){
        boolean response = reservationService.saveReservation(reservation, restaurantEmail, principal.getName());
        String restaurantName = restaurantProfileService.findByEmail(restaurantEmail).getRestaurantName();
        // If not a logged in user
        if (principal == null){
            return "redirect:/login";
        }
        // If there are no available tables
        if (response == false) {
            redirectAttributes.addFlashAttribute("error", "No available tables during this time");
            return "redirect:/restaurantInfo/" + restaurantName;

        }
        return "redirect:/customerProfile";
    }
}

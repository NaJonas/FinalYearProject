package com.navikas.finalyear.controllers;

import com.navikas.finalyear.entities.Reservation;
import com.navikas.finalyear.entities.RestaurantUser;
import com.navikas.finalyear.entities.User;
import com.navikas.finalyear.repository.CustomerUserRepository;
import com.navikas.finalyear.repository.RestaurantUserRepository;
import com.navikas.finalyear.services.ReservationService;
import com.navikas.finalyear.services.RestaurantProfileService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Controller
public class HomeController {
    private CustomerUserRepository customerUserRepository;
    private RestaurantUserRepository restaurantUserRepository;
    private RestaurantProfileService restaurantProfileService;
    private ReservationService reservationService;

    public HomeController(CustomerUserRepository customerUserRepository, RestaurantUserRepository restaurantUserRepository, RestaurantProfileService restaurantProfileService, ReservationService reservationService){
        this.customerUserRepository = customerUserRepository;
        this.restaurantUserRepository = restaurantUserRepository;
        this.restaurantProfileService = restaurantProfileService;
        this.reservationService = reservationService;
    }

    @RequestMapping("/success.html")
    public void loginRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult, ModelMap model) throws IOException {
        // Redirecting idea adapted from https://stackoverflow.com/questions/45709333/page-redirecting-depending-on-role-using-spring-security-and-thymeleaf-spring
        UserDetails userDetails = (UserDetails) authResult.getPrincipal();
        String username = userDetails.getUsername();
        if (customerUserRepository.findById(username).isPresent()){
            System.out.println("Customer logged in");
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
        }
        else if (restaurantUserRepository.findById(username).isPresent()){
            System.out.println("Restaurant logged in");
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/restaurantProfile"));
        }
    }



    // Listing all restaurants in a page
    @RequestMapping(value="/restaurantList", method = RequestMethod.GET)
    public ModelAndView showRestaurants(){
        return new ModelAndView("restaurantList", "restaurants", restaurantUserRepository.findAll());
    }

    @GetMapping("/restaurantInfo/{restaurantName}")
    public ModelAndView uniqueURL(@PathVariable("restaurantName") String restaurantName) {
        RestaurantUser user = restaurantUserRepository.findByRestaurantName(restaurantName);
        ModelAndView mva = new ModelAndView("restaurantInfo", "restaurant", user);
        mva.addObject("reservation", new Reservation());
        return mva;
    }
    @PostMapping
    @RequestMapping(value="/restaurantInfo/{restaurantName}", method = RequestMethod.POST)
    public String addReservation(Reservation reservation, Model model, @RequestParam Map<String,String> allParams, @RequestParam(value = "email")String restaurantEmail, Principal principal, RedirectAttributes redirectAttributes){
        boolean response = reservationService.saveReservation(reservation, restaurantEmail, principal.getName());
        String restaurantName = restaurantProfileService.findByEmail(restaurantEmail).getRestaurantName();
        if (response == false) {
            redirectAttributes.addFlashAttribute("error", "No available bookings during this time");
        }
        return "redirect:/restaurantInfo/" + restaurantName;
    }
}

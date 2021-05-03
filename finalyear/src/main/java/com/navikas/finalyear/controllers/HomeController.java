package com.navikas.finalyear.controllers;

import com.navikas.finalyear.entities.Reservation;
import com.navikas.finalyear.entities.RestaurantUser;
import com.navikas.finalyear.entities.Tables;
import com.navikas.finalyear.entities.User;
import com.navikas.finalyear.repository.AdminRepository;
import com.navikas.finalyear.repository.CustomerUserRepository;
import com.navikas.finalyear.repository.RestaurantUserRepository;
import com.navikas.finalyear.services.CustomerProfileService;
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
import java.util.List;
import java.util.Map;

/*
A home controller class where an implementation is for a redirecting to home pages,
create unique urls for restaurants and admin page's functionality

 */

@Controller
public class HomeController {
    private CustomerUserRepository customerUserRepository;
    private RestaurantUserRepository restaurantUserRepository;
    private RestaurantProfileService restaurantProfileService;
    private ReservationService reservationService;
    private CustomerProfileService customerProfileService;
    private AdminRepository adminRepository;

    public HomeController(CustomerUserRepository customerUserRepository, RestaurantUserRepository restaurantUserRepository, RestaurantProfileService restaurantProfileService, ReservationService reservationService, CustomerProfileService customerProfileService, AdminRepository adminRepository){
        this.customerUserRepository = customerUserRepository;
        this.restaurantUserRepository = restaurantUserRepository;
        this.restaurantProfileService = restaurantProfileService;
        this.reservationService = reservationService;
        this.customerProfileService = customerProfileService;
        this.adminRepository = adminRepository;
    }

    // Redirecting method, when after successful log in, redirect each type of user to its default page
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
        else if (adminRepository.findById(username).isPresent()){
            System.out.println("Admin logged in");
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/admin"));
        }
    }


    // Listing search results restaurants in a page
    @RequestMapping(value="/search", method = RequestMethod.GET)
    public ModelAndView showSearchResults(String searchTerm){
        List<RestaurantUser> restaurants = restaurantProfileService.getAllBySearch(searchTerm);
        return new ModelAndView("restaurantList", "restaurants", restaurants);
    }



    // Listing all restaurants in a page
    @RequestMapping(value="/restaurantList", method = RequestMethod.GET)
    public ModelAndView showRestaurants(){
        return new ModelAndView("restaurantList", "restaurants", restaurantUserRepository.findAllByIsAuthorized(true));
    }

    // Create a unique url based on the restaurant clicked from the restaurant list and show the page
    @GetMapping("/restaurantInfo/{restaurantName}")
    public ModelAndView uniqueURL(@PathVariable("restaurantName") String restaurantName) {
        RestaurantUser user = restaurantUserRepository.findByRestaurantName(restaurantName);
        ModelAndView mva = new ModelAndView("restaurantInfo", "restaurant", user);
        mva.addObject("reservation", new Reservation());
        mva.addObject("menuItems", restaurantProfileService.getMenuItemsByRestaurantName(restaurantName));
        return mva;
    }


    // Show admin page
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView showAdminPage() {
        List<RestaurantUser> tablesList = restaurantUserRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("admin", "restaurants", tablesList);
        return modelAndView;
    }

    // Authorize/Unauthorize a selected restaurant in admin page
    @RequestMapping(value = "/authorize", method = RequestMethod.POST)
    public String authorizeRestaurant(String email) {
        restaurantProfileService.authorizeRestaurant(email);
        return "redirect:/admin";
    }
}

package com.navikas.finalyear.controllers;

import com.navikas.finalyear.entities.User;
import com.navikas.finalyear.repository.CustomerUserRepository;
import com.navikas.finalyear.repository.RestaurantUserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HomeController {
    private CustomerUserRepository customerUserRepository;
    private RestaurantUserRepository restaurantUserRepository;

    public HomeController(CustomerUserRepository customerUserRepository, RestaurantUserRepository restaurantUserRepository){
        this.customerUserRepository = customerUserRepository;
        this.restaurantUserRepository = restaurantUserRepository;
    }

    @RequestMapping("/success.html")
    public void loginRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult, ModelMap model) throws IOException {
        // Redirecting idea adapted from https://stackoverflow.com/questions/45709333/page-redirecting-depending-on-role-using-spring-security-and-thymeleaf-spring
        UserDetails userDetails = (UserDetails) authResult.getPrincipal();
        String username = userDetails.getUsername();
        if (customerUserRepository.findById(username).isPresent()){
            System.out.println("Customer logged in");
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/register"));
        }
        else if (restaurantUserRepository.findById(username).isPresent()){
            System.out.println("Restaurant logged in");
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
        }
    }
}

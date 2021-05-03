package com.navikas.finalyear.controllers;


import com.navikas.finalyear.entities.CustomerUser;
import com.navikas.finalyear.entities.RestaurantUser;
import com.navikas.finalyear.entities.User;
import com.navikas.finalyear.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

// Registration controller. Idea taken from Business Systems term 2 module lectures
@Controller
public class UserRegistrationController {

    private UserService userService;
    public UserRegistrationController(UserService userService){
        this.userService = userService;
    }

    // Show the register page
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegistration(){
        CustomerUser customerUser = new CustomerUser();
        RestaurantUser restaurantUser = new RestaurantUser();
        ModelAndView mav = new ModelAndView("register", "newUser", customerUser);
        mav.addObject("newUser", restaurantUser);
        return mav;

    }
    // Register new user and redirect to /index
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(CustomerUser user, RestaurantUser rUser, @Valid String role, RedirectAttributes redirectAttributes){
        // https://stackoverflow.com/questions/58795016/how-do-i-pass-the-selected-dropdown-value-to-a-controller-in-thymeleaf
        // Checks the role chosen and registers appropriate type of user

        if (userService.alreadyExist(user.getEmail())){
            redirectAttributes.addFlashAttribute("error", "Account already exists with this email");
            return "redirect:/register";
        }
        else {

            if (role.equals("Customer")) {
                userService.registerCustomerUser(user);
                return "redirect:/";
            } else if (role.equals("Restaurant")) {
                userService.registerRestaurantUser(rUser);
                return "redirect:/";

            }
            return "redirect:/";
        }

    }



}

package com.navikas.finalyear.controllers;

import com.navikas.finalyear.entities.CustomerUser;
import com.navikas.finalyear.entities.MenuItem;
import com.navikas.finalyear.entities.RestaurantUser;
import com.navikas.finalyear.entities.Tables;
import com.navikas.finalyear.services.RestaurantProfileService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        ModelAndView modelAndView = new ModelAndView("restaurantProfile", "editProfile", restaurantUser);
        modelAndView.addObject("addMenuItem", new MenuItem());
        modelAndView.addObject("menuItems", restaurantProfileService.getMenuItems(principal.getName()));
        return  modelAndView;
    }

    @RequestMapping(value="/restaurantProfile", method = RequestMethod.POST)
    public String editProfile(RestaurantUser restaurantUser, Principal principal){
        restaurantProfileService.saveProfile(restaurantUser, principal.getName());
        return "redirect:/restaurantProfile";
    }

    @RequestMapping(value="/addMenuItem", method = RequestMethod.POST)
    public String addMenuItem(MenuItem menuItem, Principal principal){
        restaurantProfileService.addMenuItem(principal.getName(), menuItem);
        return "redirect:/restaurantProfile";
    }
    @RequestMapping(value="/updateMenu", method = RequestMethod.POST)
    public String updateMenu(MenuItem menuItem, Principal principal, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("id", menuItem.getId());
        redirectAttributes.addFlashAttribute("itemName", menuItem.getItemName());
        redirectAttributes.addFlashAttribute("price", menuItem.getPrice());
        redirectAttributes.addFlashAttribute("description", menuItem.getDescription());
        System.out.println(menuItem.getDescription());
        return "redirect:/updateMenu";
    }
    @RequestMapping(value = "/updateMenu", method = RequestMethod.GET)
    public ModelAndView updateTable(Model model) {
        return new ModelAndView("updateMenu","updateMenu", new MenuItem());
    }

    @RequestMapping(value="/editMenu", method = RequestMethod.POST)
    public String editMenu(MenuItem menuItem, Principal principal){
        restaurantProfileService.addMenuItem(principal.getName(), menuItem);
        return "redirect:/restaurantProfile";
    }
}

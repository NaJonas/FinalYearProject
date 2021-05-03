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
import java.util.List;

/*
Restaurant profile controller where all restaurant user's functionalities are processed
 */
@Controller
public class RestaurantProfileController {
    RestaurantProfileService restaurantProfileService;
    //---------------------------------------------------------------------------------------
    // Restaurant profile edit for restaurant users
    public RestaurantProfileController(RestaurantProfileService restaurantProfileService) {
        this.restaurantProfileService = restaurantProfileService;
    }


    // Show restaut profile
    @RequestMapping(value = "/restaurantProfile", method = RequestMethod.GET)
    public ModelAndView showRestaurantProfile(Principal principal){
        // Get the logged in restaurant entity and show it on the web page
        RestaurantUser restaurantUser = restaurantProfileService.findByEmail(principal.getName());
        ModelAndView modelAndView = new ModelAndView("restaurantProfile", "editProfile", restaurantUser);
        modelAndView.addObject("addMenuItem", new MenuItem());
        modelAndView.addObject("menuItems", restaurantProfileService.getMenuItems(principal.getName()));
        return  modelAndView;
    }

    // Save or update restaurant profile information
    @RequestMapping(value="/restaurantProfile", method = RequestMethod.POST)
    public String editProfile(RestaurantUser restaurantUser, Principal principal){
        restaurantProfileService.saveProfile(restaurantUser, principal.getName());
        return "redirect:/restaurantProfile";
    }

    // Post method to add a menu item in restaurant profile page
    @RequestMapping(value="/addMenuItem", method = RequestMethod.POST)
    public String addMenuItem(MenuItem menuItem, Principal principal){
        restaurantProfileService.addMenuItem(principal.getName(), menuItem);
        return "redirect:/restaurantProfile";
    }
    // Redirect to the updatemenu page and send the details of the menu item that wants to be updated
    @RequestMapping(value="/updateMenu", method = RequestMethod.POST)
    public String updateMenu(MenuItem menuItem, Principal principal, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("id", menuItem.getId());
        redirectAttributes.addFlashAttribute("itemName", menuItem.getItemName());
        redirectAttributes.addFlashAttribute("price", menuItem.getPrice());
        redirectAttributes.addFlashAttribute("description", menuItem.getDescription());
        System.out.println(menuItem.getDescription());
        return "redirect:/updateMenu";
    }
    // Update menu item page to update menu item details
    @RequestMapping(value = "/updateMenu", method = RequestMethod.GET)
    public ModelAndView updateMenu(Model model) {
        return new ModelAndView("updateMenu","updateMenu", new MenuItem());
    }

    // Update the menu item values
    @RequestMapping(value="/editMenu", method = RequestMethod.POST)
    public String editMenu(MenuItem menuItem, Principal principal){
        restaurantProfileService.addMenuItem(principal.getName(), menuItem);
        return "redirect:/restaurantProfile";
    }

    // Table management page get method
    @RequestMapping(value = "/manageTables", method = RequestMethod.GET)
    public ModelAndView showTableManagement(Principal principal, Model model) {
        // Get the logged in restaurant entity and show it on the web page
        List<Tables> tablesList = restaurantProfileService.getTables(principal.getName());
        ModelAndView modelAndView = new ModelAndView("manageTables", "tables", tablesList);
        modelAndView.addObject("addTable", new Tables());
        modelAndView.addObject("reservations", restaurantProfileService.getReservationsForProfile(principal.getName()));
        return modelAndView;
    }
    // Post method to send details to the update page
    @RequestMapping(value="/manageTables", method = RequestMethod.POST)
    public String editTables(Tables table, RedirectAttributes redirectAttributes){
        // Send table attributes to the redirect page
        redirectAttributes.addFlashAttribute("turnover", table.getTurnover());
        redirectAttributes.addFlashAttribute("tableNumber", table.getTableNumber());
        redirectAttributes.addFlashAttribute("id",table.getId());
        redirectAttributes.addFlashAttribute("capacity", table.getCapacity());
        redirectAttributes.addFlashAttribute("isAvailable", table.getIsAvailable());
        return "redirect:/update";
    }
    // Update page to update table information
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView updateTable(Model model) {
        return new ModelAndView("update","updateTable", new Tables());
    }

    // Post method for updating table information
    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String editTable(Tables table, Principal principal){
        restaurantProfileService.updateTable(table, principal.getName());

        return "redirect:/manageTables";
    }
    // Add a new table while in table management page
    @RequestMapping(value="/addTable", method = RequestMethod.POST)
    public String addTable(Tables table, Principal principal){
        restaurantProfileService.saveTable(table, restaurantProfileService.findByEmail(principal.getName()));
        return "redirect:/manageTables";
    }
}

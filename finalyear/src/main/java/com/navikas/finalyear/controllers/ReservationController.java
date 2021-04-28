package com.navikas.finalyear.controllers;

import com.navikas.finalyear.entities.RestaurantUser;
import com.navikas.finalyear.entities.Tables;
import com.navikas.finalyear.services.RestaurantProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class ReservationController {
    RestaurantProfileService restaurantProfileService;

    public ReservationController(RestaurantProfileService restaurantProfileService) {
        this.restaurantProfileService = restaurantProfileService;
    }

    @RequestMapping(value = "/manageTables", method = RequestMethod.GET)
    public ModelAndView showTableManagement(Principal principal, Model model) {
        // Get the logged in restaurant entity and show it on the web page
        List<Tables> tablesList = restaurantProfileService.getTables(principal.getName());
        ModelAndView modelAndView = new ModelAndView("manageTables", "tables", tablesList);
        modelAndView.addObject("addTable", new Tables());
        modelAndView.addObject("reservations", restaurantProfileService.getReservationsForProfile(principal.getName()));
        return modelAndView;
    }

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
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView updateTable(Model model) {
        return new ModelAndView("update","updateTable", new Tables());
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String editTable(Tables table, Principal principal){
        restaurantProfileService.updateTable(table, principal.getName());

        return "redirect:/manageTables";
    }
    @RequestMapping(value="/addTable", method = RequestMethod.POST)
    public String addTable(Tables table, Principal principal){
        restaurantProfileService.saveTable(table, restaurantProfileService.findByEmail(principal.getName()));
        return "redirect:/manageTables";
    }
}

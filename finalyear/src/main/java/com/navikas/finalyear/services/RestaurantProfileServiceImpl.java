package com.navikas.finalyear.services;

import com.navikas.finalyear.additionalclasses.TempReservation;
import com.navikas.finalyear.entities.MenuItem;
import com.navikas.finalyear.entities.Reservation;
import com.navikas.finalyear.entities.RestaurantUser;
import com.navikas.finalyear.entities.Tables;
import com.navikas.finalyear.repository.MenuItemRepository;
import com.navikas.finalyear.repository.ReservationRepository;
import com.navikas.finalyear.repository.RestaurantUserRepository;
import com.navikas.finalyear.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantProfileServiceImpl implements RestaurantProfileService{
    private RestaurantUserRepository restaurantUserRepository;
    private TableRepository tableRepository;
    private ReservationRepository reservationRepository;
    private MenuItemRepository menuItemRepository;

    @Autowired
    public RestaurantProfileServiceImpl(RestaurantUserRepository restaurantUserRepository, TableRepository tableRepository, ReservationRepository reservationRepository, MenuItemRepository menuItemRepository){
        this.restaurantUserRepository = restaurantUserRepository;
        this.tableRepository = tableRepository;
        this.reservationRepository = reservationRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public void saveProfile(RestaurantUser restaurantUser, String email){
        RestaurantUser user = restaurantUserRepository.findById(email).orElse(null);
        user.setAddress(restaurantUser.getAddress());
        user.setRestaurantName(restaurantUser.getRestaurantName());
        user.setDescription(restaurantUser.getDescription());
        restaurantUserRepository.save(user);
    }
    @Override
    public RestaurantUser findByEmail(String email){
        return restaurantUserRepository.findById(email).orElse(null);
    }
    @Override
    public List<RestaurantUser> findAll(){
        return restaurantUserRepository.findAll();
    }

    @Override
    public List<Tables> getTables(String restaurantEmail){
        return tableRepository.findAllByRestaurantEmail(restaurantEmail);
    }

    @Override
    public void updateTable(Tables table, String restaurantEmail){
        Tables newTable = tableRepository.findByIdAndRestaurantEmail(table.getId(),restaurantEmail);
        newTable.setCapacity(table.getCapacity());
        newTable.setIsAvailable(table.getIsAvailable());
        newTable.setTurnover(table.getTurnover());
        tableRepository.save(newTable);
    }
    @Override
    public void saveTable(Tables table, RestaurantUser restaurantUser){
        table.setRestaurant(restaurantUser);
        // Default availability - true
        table.setIsAvailable(true);
        tableRepository.save(table);
    }
    @Override
    public List<TempReservation> getReservationsForProfile(String restaurantEmail){
        List<Reservation> reservations = reservationRepository.findAllByRestaurantEmail(restaurantEmail);
        List<TempReservation> tempList = new ArrayList<TempReservation>();
        // Go through the reservation list and create a new temp list with additional fields for th:each
        for (Reservation reservation : reservations){
            Long id = reservation.getId();
            Date reservationDate = reservation.getReservationDate();
            LocalTime startTime = reservation.getStartTime();
            LocalTime endTime = reservation.getEndTime();
            int people = reservation.getPeople();

            // Customer information
            String name = reservation.getCustomer().getName();
            String surname = reservation.getCustomer().getSurname();

            int tableNumber = reservation.getTable().getTableNumber();

            tempList.add(new TempReservation(id, reservationDate, startTime, endTime, people, name, surname, tableNumber));
        }
        return tempList;
    }

    @Override
    public void addMenuItem(String restaurantEmail, MenuItem menuItem){
        menuItem.setRestaurant(restaurantUserRepository.findById(restaurantEmail).orElse(null));
        menuItemRepository.save(menuItem);
    }
    @Override
    public List<MenuItem> getMenuItems(String restaurantEmail){
        return menuItemRepository.findAllByRestaurantEmail(restaurantEmail);
    }

    @Override
    public List<MenuItem> getMenuItemsByRestaurantName(String restaurantName){
        return menuItemRepository.findAllByRestaurantRestaurantName(restaurantName);
    }
    @Override
    public List<RestaurantUser> getAllBySearch(String restaurantName){
        return restaurantUserRepository.findAllByRestaurantNameContainsIgnoreCaseAndIsAuthorized(restaurantName, true);
    }
    @Override
    public void authorizeRestaurant(String restaurantEmail){
        RestaurantUser restaurant = restaurantUserRepository.findById(restaurantEmail).orElse(null);
        if (restaurant.getIsAuthorized() == false) {
            restaurant.setIsAuthorized(true);
        }
        else restaurant.setIsAuthorized(false);
        restaurantUserRepository.save(restaurant);
    }





}

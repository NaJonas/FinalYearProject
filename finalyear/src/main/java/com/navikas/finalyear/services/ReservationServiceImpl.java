package com.navikas.finalyear.services;

import com.navikas.finalyear.config.SortByCapacity;
import com.navikas.finalyear.entities.CustomerUser;
import com.navikas.finalyear.entities.Reservation;
import com.navikas.finalyear.entities.RestaurantUser;
import com.navikas.finalyear.entities.Tables;
import com.navikas.finalyear.repository.CustomerUserRepository;
import com.navikas.finalyear.repository.ReservationRepository;
import com.navikas.finalyear.repository.RestaurantUserRepository;
import com.navikas.finalyear.repository.TableRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;


@Service
public class ReservationServiceImpl implements ReservationService{
    private ReservationRepository reservationRepository;
    private RestaurantUserRepository restaurantUserRepository;
    private TableRepository tableRepository;
    private CustomerUserRepository customerUserRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, RestaurantUserRepository restaurantUserRepository, TableRepository tableRepository, CustomerUserRepository customerUserRepository) {
        this.reservationRepository = reservationRepository;
        this.restaurantUserRepository = restaurantUserRepository;
        this.tableRepository = tableRepository;
        this.customerUserRepository = customerUserRepository;
    }

    @Override
    public boolean saveReservation(Reservation reservation, String restaurantEmail, String customerEmail){

        CustomerUser customer = customerUserRepository.findById(customerEmail).orElse(null);
        RestaurantUser restaurant = restaurantUserRepository.findById(restaurantEmail).orElse(null);
        reservation.setCustomer(customer);
        reservation.setRestaurant(restaurant);



        // Get all available (NOT BLOCKED by a restaurant) tables from the restaurant that the customer is trying to book a table with
        List<Tables> tableList = tableRepository.findByRestaurantEmailAndIsAvailable(restaurantEmail, true);
        // Make an iterator in case there are no free tables left, and the list gets empty
        Iterator<Tables> iterator = tableList.iterator();
        Date reservationDate = reservation.getReservationDate();
        LocalTime reservationStartTime = reservation.getStartTime();
        while (iterator.hasNext()){
            Tables table = iterator.next();
            // Check if the table's max capacity is lower than the people in the booking
            if (table.getCapacity() < reservation.getPeople()){
                iterator.remove();
            }
            // Get reservation end time for this particular table
            LocalTime reservationEndTime = reservationStartTime.plusMinutes(table.getTurnover());
            // Get all table reservations
            List<Reservation> reservationList = table.getReservations();
            for (Reservation r : reservationList){
                System.out.println(r.getStartTime());
                // If the reservation date from the database is the same as the reservation date the customer is trying to make
                if (r.getReservationDate().equals(reservationDate)){
                    // Boolean values for checking if the current reservation overlaps with a reservation from the database
                    boolean lessThan = reservationEndTime.isBefore(r.getStartTime()) || reservationEndTime.equals(r.getStartTime());
                    boolean moreThan = reservationStartTime.isAfter(r.getEndTime()) || reservationStartTime.equals(r.getEndTime());
                    System.out.println(lessThan);
                    System.out.println(moreThan);
                    //if (reservationEndTime <= r.getStartTime() || reservationStartTime >= r.getEndTime())
                    if (!(lessThan || moreThan)){
                        iterator.remove();
                        break;
                    }
                }
            }
        }
        for (Tables table : tableList){
            System.out.println(table.getId());
        }
        if (tableList.isEmpty()){
            return false;
        }
        else {
            // Sort the available tables by capacity and pick the first (lowest), add it and save to the database
            tableList.sort(new SortByCapacity());
            Tables picked = tableList.get(0);
            reservation.setEndTime(reservationStartTime.plusMinutes(picked.getTurnover()));
            reservation.setTable(picked);
            reservationRepository.save(reservation);
            return true;
        }






    }

}

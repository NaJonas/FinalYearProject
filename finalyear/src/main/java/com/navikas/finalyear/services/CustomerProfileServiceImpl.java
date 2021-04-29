package com.navikas.finalyear.services;


import com.navikas.finalyear.additionalclasses.TempReservation;
import com.navikas.finalyear.entities.CustomerUser;
import com.navikas.finalyear.entities.Reservation;
import com.navikas.finalyear.repository.CustomerUserRepository;
import com.navikas.finalyear.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerProfileServiceImpl implements CustomerProfileService{
    private ReservationRepository reservationRepository;
    private CustomerUserRepository customerUserRepository;

    public CustomerProfileServiceImpl(ReservationRepository reservationRepository, CustomerUserRepository customerUserRepository) {
        this.reservationRepository = reservationRepository;
        this.customerUserRepository = customerUserRepository;
    }

    @Override
    public List<TempReservation> getCustomerReservations(String customerEmail){
        List<Reservation> reservations = reservationRepository.findAllByCustomerEmail(customerEmail);
        List<TempReservation> tempReservations = new ArrayList<TempReservation>();
        for (Reservation reservation : reservations){
            Long id = reservation.getId();
            String restaurantName = reservation.getRestaurant().getRestaurantName();
            Date reservationDate = reservation.getReservationDate();
            LocalTime startTime = reservation.getStartTime();
            TempReservation tmp = new TempReservation();

            tmp.setId(id);
            tmp.setRestaurantName(restaurantName);
            tmp.setReservationDate(reservationDate);
            tmp.setStartTime(startTime);
            tempReservations.add(tmp);

        }
        return tempReservations;
    }

    @Override
    public CustomerUser findByEmail(String email){
        return customerUserRepository.findById(email).orElse(null);
    }

    @Override
    public void deleteReseration(Long id){
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        reservationRepository.delete(reservation);
    }
    @Override
    public boolean isLoggedIn(String customerEmail){
        return customerUserRepository.findById(customerEmail).isPresent();
    }
    @Override
    public void changePassword(String customerEmail, String password){
        CustomerUser user = customerUserRepository.findById(customerEmail).orElse(null);
        user.setPassword(password);
        customerUserRepository.save(user);
    }
    @Override
    public void deleteUser(String customerEmail){
        List<Reservation> reservations = reservationRepository.findAllByCustomerEmail(customerEmail);
        // Delete all reservations first then delete the user account
        for (Reservation reservation : reservations){
            reservationRepository.delete(reservation);
        }
        customerUserRepository.delete(customerUserRepository.findById(customerEmail).orElse(null));
    }




}

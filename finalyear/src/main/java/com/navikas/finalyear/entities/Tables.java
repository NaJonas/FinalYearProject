package com.navikas.finalyear.entities;


import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Tables {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private int tableNumber;
    private Boolean isAvailable;
    private int capacity;
    private int turnover;
    @ManyToOne
    @JoinColumn(name = "restaurant_email")
    private RestaurantUser restaurant;

    @OneToMany(mappedBy = "table")
    private List<Reservation> reservations;

    public Tables(long id, Boolean isAvailable, int capacity, RestaurantUser restaurant, List<Reservation> reservations, int tableNumber, int turnover) {
        this.id = id;
        this.isAvailable = isAvailable;
        this.capacity = capacity;
        this.restaurant = restaurant;
        this.reservations = reservations;
        this.tableNumber = tableNumber;
        this.turnover = turnover;
    }
    public Tables(){}

    public int getTurnover() {
        return turnover;
    }

    public void setTurnover(int turnover) {
        this.turnover = turnover;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean available) {
        isAvailable = available;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public RestaurantUser getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantUser restaurant) {
        this.restaurant = restaurant;
    }
}
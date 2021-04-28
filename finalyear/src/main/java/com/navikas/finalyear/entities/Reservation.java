package com.navikas.finalyear.entities;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

@Entity
public class Reservation {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Date reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private int people;
    @ManyToOne
    @JoinColumn(name = "restaurant_email")
    private RestaurantUser restaurant;

    @ManyToOne
    @JoinColumn(name = "customer_email")
    private CustomerUser customer;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private Tables table;

    public Reservation(Long id, Date reservationDate, LocalTime startTime, LocalTime endTime, RestaurantUser restaurant, CustomerUser customer, Tables table, int people) {
        this.id = id;
        this.reservationDate = reservationDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.restaurant = restaurant;
        this.customer = customer;
        this.table = table;
        this.people = people;
    }
    public Reservation(){}

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public Tables getTable() {
        return table;
    }

    public void setTable(Tables table) {
        this.table = table;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public RestaurantUser getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantUser restaurant) {
        this.restaurant = restaurant;
    }

    public CustomerUser getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerUser customer) {
        this.customer = customer;
    }
}

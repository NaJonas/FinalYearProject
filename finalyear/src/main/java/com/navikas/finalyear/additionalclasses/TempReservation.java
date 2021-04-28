package com.navikas.finalyear.additionalclasses;

/*


This class is a temporary class for reservation to add other fields from One to Many mapping (CustomerUser, Tables)
for thymeleaf th:each reservations list for a restaurant table management page.


 */

import java.sql.Date;
import java.time.LocalTime;

public class TempReservation {
    private Long id;
    private Date reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private int people;

    // Customer information
    private String name;
    private String surname;

    // Table information
    private int tableNumber;

    // Restaurant information
    private String restaurantName;

    public TempReservation(Long id, Date reservationDate, LocalTime startTime, LocalTime endTime, int people, String name, String surname, int tableNumber) {
        this.id = id;
        this.reservationDate = reservationDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.people = people;
        this.name = name;
        this.surname = surname;
        this.tableNumber = tableNumber;
    }
    public TempReservation(){}

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
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

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}

package com.navikas.finalyear.entities;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class CustomerUser extends User {
    private String surname;
    private String name;
    @OneToMany(mappedBy = "customer")
    private List<Reservation> reservationList;

    public CustomerUser(String email, String password, String name, String surname, List<Reservation> reservationList) {
        super(email, password);
        this.name = name;
        this.surname = surname;
        this.reservationList = reservationList;
    }



    public CustomerUser() {

    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
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

package com.navikas.finalyear.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity

public class CustomerUser extends User {
    @NotNull
    private String surname;
    @NotNull
    private String name;

    public CustomerUser(String email, String password, String name, String surname) {
        super(email, password);
        this.name = name;
        this.surname = surname;
    }



    public CustomerUser() {

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

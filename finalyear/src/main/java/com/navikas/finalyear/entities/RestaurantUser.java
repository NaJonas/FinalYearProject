package com.navikas.finalyear.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class RestaurantUser extends User{
    @NotNull
    @NotEmpty
    private String companyName;
    private Boolean isAuthorized;

    public RestaurantUser(String email, String password, String companyName, Boolean isAuthorized) {
        super(email, password);
        this.companyName = companyName;
        this.isAuthorized = isAuthorized;
    }


    public RestaurantUser() {

    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Boolean getAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(Boolean authorized) {
        isAuthorized = authorized;
    }
}


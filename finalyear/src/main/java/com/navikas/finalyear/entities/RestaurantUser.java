package com.navikas.finalyear.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class RestaurantUser extends User{
    @NotNull
    @NotEmpty
    private String companyName;
    private Boolean isAuthorized;

    private String restaurantName;
    private String description;
    private String address;
    @OneToMany(mappedBy = "restaurantUser")
    private List<MenuSection> menuSectionList;
    @OneToMany(mappedBy = "restaurant")
    private List<Tables> tablesList;
    @OneToMany(mappedBy = "restaurant")
    private List<Reservation> reservationList;


    public RestaurantUser(String email, String password, String companyName, Boolean isAuthorized, String restaurantName, String description, String address, List<MenuSection> menuSections,
                            List<Tables> tablesList, List<Reservation> reservationList) {
        super(email, password);
        this.companyName = companyName;
        this.isAuthorized = isAuthorized;
        this.restaurantName = restaurantName;
        this.description = description;
        this.address = address;
        this.menuSectionList = menuSections;
        this.tablesList = tablesList;
        this.reservationList = reservationList;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<MenuSection> getMenuSectionList() {
        return menuSectionList;
    }

    public void setMenuSectionList(List<MenuSection> menuSectionList) {
        this.menuSectionList = menuSectionList;
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

    public List<Tables> getTablesList() {
        return tablesList;
    }

    public void setTablesList(List<Tables> tablesList) {
        this.tablesList = tablesList;
    }
}


package com.navikas.finalyear.entities;


import javax.persistence.*;
import java.util.List;

@Entity
public class MenuSection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String sectionName;
    @OneToMany(mappedBy = "menuSection")
    private List<Item> items;
    @ManyToOne
    @JoinColumn(name="restaurant_email")
    private RestaurantUser restaurantUser;

    public MenuSection(String sectionName, List<Item> items, RestaurantUser restaurantUser) {
        this.sectionName = sectionName;
        this.items = items;
        this.restaurantUser = restaurantUser;
    }
    public MenuSection(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public RestaurantUser getRestaurantUser() {
        return restaurantUser;
    }

    public void setRestaurantUser(RestaurantUser restaurantUser) {
        this.restaurantUser = restaurantUser;
    }
}

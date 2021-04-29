package com.navikas.finalyear.entities;


import javax.persistence.*;

@Entity
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String itemName;
    @Column(length = 3000)
    private String description;
    private String price;
    @ManyToOne
    @JoinColumn(name = "restaurant_email")
    private RestaurantUser restaurant;


    public MenuItem(String email, String itemName, String description, String price, RestaurantUser restaurantUser) {
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.restaurant = restaurantUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public MenuItem() {

    }

    public RestaurantUser getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantUser restaurant) {
        this.restaurant = restaurant;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

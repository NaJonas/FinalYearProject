package com.navikas.finalyear.entities;


import javax.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String itemName;
    private String description;
    @ManyToOne
    @JoinColumn(name="restaurant_email")
    private MenuSection menuSection;

    public Item(String email, String itemName, String description, MenuSection menuSection) {
        this.itemName = itemName;
        this.description = description;
        this.menuSection = menuSection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MenuSection getMenuSection() {
        return menuSection;
    }

    public void setMenuSection(MenuSection menuSection) {
        this.menuSection = menuSection;
    }

    public Item() {

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

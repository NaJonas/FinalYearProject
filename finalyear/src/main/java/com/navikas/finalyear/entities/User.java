package com.navikas.finalyear.entities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@MappedSuperclass
// Main user entity
public class User {
    @Id
    private String email;
    private String password;

    public User(String email, String password) {
        this.email = email;
        setPassword(password);
    }

    public User() {

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = new BCryptPasswordEncoder().encode(password);
    }


}

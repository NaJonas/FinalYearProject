package com.navikas.finalyear.config;

import com.navikas.finalyear.entities.Admin;
import com.navikas.finalyear.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/*
A class created to automatically insert admin user into the database upon start up
Taken from: https://dzone.com/articles/spring-boot-applicationrunner-and-commandlinerunne

 */

@Component
public class CommandLineAppStartupRunner implements ApplicationRunner {
    @Autowired
    AdminRepository adminRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception{
        Admin admin = new Admin();
        admin.setEmail("admin");
        admin.setPassword("admin");
        adminRepository.save(admin);
    }
}

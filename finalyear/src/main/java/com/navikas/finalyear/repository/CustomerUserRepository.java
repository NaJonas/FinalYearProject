package com.navikas.finalyear.repository;

import com.navikas.finalyear.entities.CustomerUser;
import com.navikas.finalyear.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerUserRepository extends JpaRepository<CustomerUser, String> {
}

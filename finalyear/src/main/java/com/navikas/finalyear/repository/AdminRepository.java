package com.navikas.finalyear.repository;

import com.navikas.finalyear.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {
}

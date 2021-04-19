package com.navikas.finalyear.repository;

import com.navikas.finalyear.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository <Item, Long> {
}

package com.navikas.finalyear.config;

import com.navikas.finalyear.entities.Tables;

import java.util.Comparator;


// Implemented from https://howtodoinjava.com/java/collections/arraylist/arraylist-sort-objects-by-field/
public class SortByCapacity implements Comparator<Tables> {
    @Override
    public int compare(Tables t1, Tables t2){
        return t1.getCapacity() - t2.getCapacity();
    }
}

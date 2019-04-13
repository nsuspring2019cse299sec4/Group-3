package com.example.khujo.models;

import java.io.Serializable;

public class Category implements Serializable {
    private String category;
    private String placeType;

    public Category() {
    }

    public Category(String category, String placeType) {
        this.category = category;
        this.placeType = placeType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }
}

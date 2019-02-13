package com.emargystudio.bohemeav0021.Model;

public class FoodCategory {
    private int id;
    private String name;
    private String image_url;

    public FoodCategory() {
    }

    public FoodCategory(String name, String image_url) {
        this.name = name;
        this.image_url = image_url;
    }

    public FoodCategory(int id, String name, String image_url) {
        this.id = id;
        this.name = name;
        this.image_url = image_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}

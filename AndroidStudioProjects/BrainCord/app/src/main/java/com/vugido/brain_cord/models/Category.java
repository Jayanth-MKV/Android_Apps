package com.vugido.brain_cord.models;

public class Category {

    int image;
    String title;
    int id;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category(int image, String title, int id) {
        this.image = image;
        this.title = title;
        this.id = id;
    }
}

package com.vugido.ap.sklm.home_interiors.models.HomePageModels;

public class SlidersModel {

    private int image;
    private String Title;
    private int id;

    public SlidersModel(int image, String title, int id) {
        this.image = image;
        Title = title;
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

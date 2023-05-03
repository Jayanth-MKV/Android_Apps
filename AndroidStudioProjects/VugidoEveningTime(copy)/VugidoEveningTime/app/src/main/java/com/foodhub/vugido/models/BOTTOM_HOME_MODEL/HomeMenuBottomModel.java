package com.foodhub.vugido.models.BOTTOM_HOME_MODEL;

public class HomeMenuBottomModel {

    int image;
    String title;
    int id;

    public HomeMenuBottomModel(int image, String title, int id) {
        this.image = image;
        this.title = title;
        this.id = id;
    }

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
}

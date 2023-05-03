package com.vugido.vugidoupdate.models.home_page;

public class SquareMiniModel {

    String image;
    String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SquareMiniModel(String image, String title) {
        this.image = image;
        this.title = title;
    }
}

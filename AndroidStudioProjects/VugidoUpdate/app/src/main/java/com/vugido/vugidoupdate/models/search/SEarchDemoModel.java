package com.vugido.vugidoupdate.models.search;

public class SEarchDemoModel {

    int image;
    String title;

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

    public SEarchDemoModel(int image, String title) {
        this.image = image;
        this.title = title;
    }
}

package com.vugido.vugidoupdate.models.home_page;

public class CircularViewModel {

    String Title;
    String Image;

    public CircularViewModel(String title, String image) {
        Title = title;
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}

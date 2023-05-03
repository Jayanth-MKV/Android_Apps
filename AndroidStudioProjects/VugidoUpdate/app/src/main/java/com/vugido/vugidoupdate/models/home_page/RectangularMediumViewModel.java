package com.vugido.vugidoupdate.models.home_page;

public class RectangularMediumViewModel {

    String image;
    String title;
    float rating;

    public RectangularMediumViewModel(String image, String title, float rating) {
        this.image = image;
        this.title = title;
        this.rating = rating;
    }

    public String  getImage() {
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}

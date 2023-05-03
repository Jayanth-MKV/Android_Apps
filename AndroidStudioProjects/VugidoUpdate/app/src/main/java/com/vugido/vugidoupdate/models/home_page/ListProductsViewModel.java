package com.vugido.vugidoupdate.models.home_page;

public class ListProductsViewModel {

    int image;
    String title,price,type,offer,description;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ListProductsViewModel(int image, String title, String price, String type, String offer, String description) {
        this.image = image;
        this.title = title;
        this.price = price;
        this.type = type;
        this.offer = offer;
        this.description = description;
    }
}

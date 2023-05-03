package com.foodhub.vugido.models.product;

public class ProductModel {

    int image;
    String title,offer,des,price;

    public ProductModel(int image, String title, String offer, String des, String price) {
        this.image = image;
        this.title = title;
        this.offer = offer;
        this.des = des;
        this.price = price;
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

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

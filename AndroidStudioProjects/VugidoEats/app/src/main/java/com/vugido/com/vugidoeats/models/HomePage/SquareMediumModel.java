package com.vugido.com.vugidoeats.models.HomePage;

public class SquareMediumModel {
    String image;
    String title;
    String price;
    String des;

    public String getImage() {
        return image;
    }

    public void setImage(String  image) {
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public SquareMediumModel(String image, String title, String price, String des) {
        this.image = image;
        this.title = title;
        this.price = price;
        this.des = des;
    }
}

package com.vugido.online_groceries.models.account;

public class AccountOptionsModel {


    int image;
    String t1;
    String t2;
    int id;

    public AccountOptionsModel(int image, String t1, String t2, int id) {
        this.image = image;
        this.t1 = t1;
        this.t2 = t2;
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

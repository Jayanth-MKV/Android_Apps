package com.vugido.ap.sklm.hungrybirdsadmin.models;

public class DashBoardModel {

    private int image;
    private String Title;
    private String Value;

    public DashBoardModel(int image, String title, String value) {
        this.image = image;
        Title = title;
        Value = value;
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

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}

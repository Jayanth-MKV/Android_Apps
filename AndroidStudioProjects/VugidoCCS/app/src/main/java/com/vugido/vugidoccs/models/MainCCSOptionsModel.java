package com.vugido.vugidoccs.models;

public class MainCCSOptionsModel {

    String title;
    String bgColor;

    public MainCCSOptionsModel(String title, String bgColor) {
        this.title = title;
        this.bgColor = bgColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }
}

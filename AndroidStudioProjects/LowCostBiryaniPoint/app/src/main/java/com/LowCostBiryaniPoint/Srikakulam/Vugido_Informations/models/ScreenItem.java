package com.LowCostBiryaniPoint.Srikakulam.Vugido_Informations.models;

public class ScreenItem {


    private String Title,Description;
    private int ScreenImg;

    public ScreenItem(String title, String description,int Image) {
        Title = title;
        Description = description;
        ScreenImg = Image;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setScreenImg(int screenImg) {
        ScreenImg = screenImg;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public int getScreenImg() {
        return ScreenImg;
    }
}

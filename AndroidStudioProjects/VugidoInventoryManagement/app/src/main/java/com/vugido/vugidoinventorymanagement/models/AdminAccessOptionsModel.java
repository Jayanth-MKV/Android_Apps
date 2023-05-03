package com.vugido.vugidoinventorymanagement.models;

public class AdminAccessOptionsModel {

    private String AccessName;
    private int AID;
    private String BackgroundColor;
    private String TextColor;

    public AdminAccessOptionsModel(String accessName, int AID, String backgroundColor, String textColor) {
        AccessName = accessName;
        this.AID = AID;
        BackgroundColor = backgroundColor;
        TextColor = textColor;
    }

    public String getAccessName() {
        return AccessName;
    }

    public void setAccessName(String accessName) {
        AccessName = accessName;
    }

    public int getAID() {
        return AID;
    }

    public void setAID(int AID) {
        this.AID = AID;
    }

    public String getBackgroundColor() {
        return BackgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        BackgroundColor = backgroundColor;
    }

    public String getTextColor() {
        return TextColor;
    }

    public void setTextColor(String textColor) {
        TextColor = textColor;
    }
}

package com.jntuk.ucev.placementsportal.models.discussion_board;

public class ChatsModel {
    int id;
    int image;
    String name;
    String text;
    String mcount;
    String date;

    public ChatsModel(int id, int image, String name, String text, String mcount, String date) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.text = text;
        this.mcount = mcount;
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMcount() {
        return mcount;
    }

    public void setMcount(String mcount) {
        this.mcount = mcount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

package com.jntuk.ucev.placementsportal.models.home;

public class HomePostsModel {
    int id;
    int image;
    String title;
    String description;
    String link;
    String tag;


    public HomePostsModel(int id, int image, String title, String description, String link, String tag) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.link = link;
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

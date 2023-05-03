package com.jntuk.ucev.placementsportal.models.home;

public class HomeProjectsPostModel {
    int id;
    int simage;
    int pimage;
    String s_name;
    String s_info;
    String title;
    String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSimage() {
        return simage;
    }

    public void setSimage(int simage) {
        this.simage = simage;
    }

    public int getPimage() {
        return pimage;
    }

    public void setPimage(int pimage) {
        this.pimage = pimage;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_info() {
        return s_info;
    }

    public void setS_info(String s_info) {
        this.s_info = s_info;
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

    public HomeProjectsPostModel(int id, int simage, int pimage, String s_name, String s_info, String title, String description) {
        this.id = id;
        this.simage = simage;
        this.pimage = pimage;
        this.s_name = s_name;
        this.s_info = s_info;
        this.title = title;
        this.description = description;
    }
}

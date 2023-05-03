package com.vugido.online_groceries.models.homepage;

public class CategoryModel {

    private String Title;
    private int Image;
    private int ID;


    public CategoryModel(String title, int image, int ID) {
        Title = title;
        Image = image;
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}

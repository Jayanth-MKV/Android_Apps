package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models;

public class CategoryModel {

    private String Title;
    private String Image;
    private int ID;


    public CategoryModel(String title, String image, int ID) {
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

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}

package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models;

public class HorizontalCategoryModel {


    private String Title;

    public HorizontalCategoryModel(String title, String image) {
        Title = title;
        Image = image;
    }

    private String Image;

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
}

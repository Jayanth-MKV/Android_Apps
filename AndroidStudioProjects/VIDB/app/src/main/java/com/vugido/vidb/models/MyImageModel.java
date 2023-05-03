package com.vugido.vidb.models;

public class MyImageModel {

    int Image;
    int image_id;

    public MyImageModel(int image, int image_id) {
        Image = image;
        this.image_id = image_id;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }
}

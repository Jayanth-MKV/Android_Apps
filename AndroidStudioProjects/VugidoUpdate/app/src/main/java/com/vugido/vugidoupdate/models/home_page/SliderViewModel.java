package com.vugido.vugidoupdate.models.home_page;

public class SliderViewModel {

    String Image;

    public SliderViewModel(String image, int ID) {
        Image = image;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    int ID;

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

}

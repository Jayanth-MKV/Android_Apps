package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models;

public class StripBanner {

    private String Image;
    private String BG;

    public StripBanner(String image, String BG) {
        Image = image;
        this.BG = BG;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getBG() {
        return BG;
    }

    public void setBG(String BG) {
        this.BG = BG;
    }
}

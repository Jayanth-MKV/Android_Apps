package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models;

public class PocketProductModel {

    private int PocketProductImage;
    private String PocketProductName;

    public PocketProductModel(int pocketProductImage, String pocketProductName) {
        PocketProductImage = pocketProductImage;
        PocketProductName = pocketProductName;
    }

    public int getPocketProductImage() {
        return PocketProductImage;
    }

    public void setPocketProductImage(int pocketProductImage) {
        PocketProductImage = pocketProductImage;
    }

    public String getPocketProductName() {
        return PocketProductName;
    }

    public void setPocketProductName(String pocketProductName) {
        PocketProductName = pocketProductName;
    }
}

package com.vugido.ap.sklm.home_interiors.models.HomePageModels;

public class ServiceCategoryProducts {

    private int sid;
    private String title;

    public ServiceCategoryProducts(int sid, String title, int image, int cid, String description) {
        this.sid = sid;
        this.title = title;
        this.image = image;
        this.cid = cid;
        this.description = description;
    }

    private int image;
    private int cid;
    private String description;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

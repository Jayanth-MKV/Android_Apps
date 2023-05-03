package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.models.notifications;

public class NotificationData {

    private String Title;
    private String Description;
    private String Image;

    public NotificationData(String title, String description, String image) {
        Title = title;
        Description = description;
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}

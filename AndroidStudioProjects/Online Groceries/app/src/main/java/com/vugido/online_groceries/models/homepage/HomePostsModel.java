package com.vugido.online_groceries.models.homepage;

public class HomePostsModel {
    int id;
    int image;
//    /String title;
//    String description;
//    String link;
//    String tag;


    public HomePostsModel(int id, int image) {
        this.id = id;
        this.image = image;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


}

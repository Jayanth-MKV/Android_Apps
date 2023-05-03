package com.vugido.brain_cord.models;

public class Quizz {

    int image;
    int id;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Quizz(int image, int id) {
        this.image = image;
        this.id = id;
    }
}

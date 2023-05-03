package com.vugido.helloworld;

public class LearningModel {

    int id;
    int Image;
    String learning_title;

    public LearningModel(int id, int image, String learning_title) {
        this.id = id;
        Image = image;
        this.learning_title = learning_title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getLearning_title() {
        return learning_title;
    }

    public void setLearning_title(String learning_title) {
        this.learning_title = learning_title;
    }
}

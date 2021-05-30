package com.example.lesson6;

public class DataLike {
    private String caption;
    private String description;
    private int image;
    private boolean like;

    public DataLike(String caption, String description, int image, boolean like) {
        this.caption = caption;
        this.description = description;
        this.image = image;
        this.like = like;
    }

    public String getCaption() {
        return caption;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    public boolean isLike() {
        return like;
    }
}

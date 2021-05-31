package com.jansmoneymachine.timetablestudents.Main;

public class CardItem {
    private String name;
    private String description;
    private int imagePath;

    public CardItem(String name, String description, int imagePath) {
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
    }


    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getImagePath() {
        return imagePath;
    }
}

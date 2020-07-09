package com.example.drivncook;

public class Promotion {

    private String text, name;
    private int id;

    public Promotion(){}

    public Promotion(String text, String name, int id) {
        this.text = text;
        this.name = name;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

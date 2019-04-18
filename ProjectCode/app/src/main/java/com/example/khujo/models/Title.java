package com.example.khujo.models;

import java.io.Serializable;

public class Title implements Serializable {
    private String title;
    private String id;

    public Title() {
    }

    public Title(String title, String id) {
        this.title = title;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

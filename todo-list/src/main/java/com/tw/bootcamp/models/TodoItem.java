package com.tw.bootcamp.models;

import java.util.Date;
import java.util.HashMap;

public class TodoItem {
    private int id;
    private String title;
    private String createdDatetime;
    private String updatedDatetime;
    private boolean completed;

    public TodoItem(int id, String title) {
        Date now = new Date();

        this.id = id;
        this.title = title;
        this.createdDatetime = now.toString();
        this.updatedDatetime = now.toString();
        this.completed = false;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUpdatedDatetime() {
        Date now = new Date();
        this.updatedDatetime = now.toString();
    }

    public String getTitle() {
        return this.title;
    }

    public int getId() {
        return this.id;
    }

    public boolean getCompleted() {
        return completed;
    }


    public String getUpdatedDatetime() {
        return updatedDatetime;
    }

    public String getCreatedDatetime() {
        return createdDatetime;
    }
}

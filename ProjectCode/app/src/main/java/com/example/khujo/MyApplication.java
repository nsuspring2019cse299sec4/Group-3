package com.example.play.bazarmind;

import android.app.Application;
import android.content.Intent;

public class MyApplication extends Application {
    private String listId;
    private static MyApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        startService(new Intent(this, MyService.class));
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public static MyApplication getInstance() {
        return sInstance;
    }
}

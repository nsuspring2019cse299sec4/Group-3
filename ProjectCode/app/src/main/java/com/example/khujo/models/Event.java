package com.example.khujo.models;

import java.io.Serializable;

public class Event implements Serializable {
    private String id;
    private String name;
    private String venue;
    private String info;
    private String time;
    private String date;
    private boolean hasReminder;
    private String latitude, longitude;

    public Event() {
    }

    public Event(String id, String name, String venue, String info, String time, String date, boolean hasReminder, String latitude, String longitude) {
        this.id = id;
        this.name = name;
        this.venue = venue;
        this.info = info;
        this.time = time;
        this.date = date;
        this.hasReminder = hasReminder;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isHasReminder() {
        return hasReminder;
    }

    public void setHasReminder(boolean hasReminder) {
        this.hasReminder = hasReminder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

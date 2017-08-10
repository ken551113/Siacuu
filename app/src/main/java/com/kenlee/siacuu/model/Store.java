package com.kenlee.siacuu.model;

/**
 * Created by kenlee on 2017/2/20.
 */

public class Store {

    private String image, title, time, machine;

    public Store(){

    }

    public Store(String image, String title, String time, String machine) {
        this.image = image;
        this.title = title;
        this.time = time;
        this.machine = machine;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }
}

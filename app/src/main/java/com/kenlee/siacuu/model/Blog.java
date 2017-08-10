package com.kenlee.siacuu.model;

/**
 * Created by kenlee on 2017/2/15.
 */

public class Blog {

    private  String title;
    private String time;
    private String image;
    private String organization;
    private String location;
    private String desc;

    public Blog(String title, String time, String image, String organization, String location, String desc) {
        this.title = title;
        this.time = time;
        this.image = image;
        this.organization = organization;
        this.location = location;
        this.desc = desc;
    }

    public  Blog(){

    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}

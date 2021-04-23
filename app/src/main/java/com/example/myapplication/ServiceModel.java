package com.example.myapplication;

public class ServiceModel {
    String description;
    String email;
    String name;
    String phone;
    String service;
    int rating;
    String id;
     ServiceModel() {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public ServiceModel(String description, String email, String name, String phone , String service, String id, int rating) {
        this.description = description;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.service = service;
        this.id = id;
        this.rating = rating;
    }
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

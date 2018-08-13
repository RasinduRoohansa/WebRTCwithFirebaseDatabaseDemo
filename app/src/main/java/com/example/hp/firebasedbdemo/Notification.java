package com.example.hp.firebasedbdemo;

public class Notification {

    String selectedType;
    String notification;

    public Notification(){

    }

    public Notification(String selectedType, String notification) {
        this.selectedType = selectedType;
        this.notification = notification;
    }

    public String getMobileNumber() {
        return selectedType;
    }

    public String getNotification() {
        return notification;
    }
}

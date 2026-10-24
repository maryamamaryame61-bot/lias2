package com.lias.model;

public class Notification {

    private String titre;
    private String message;
    private String date;

    public Notification(String titre, String message, String date) {
        this.titre = titre;
        this.message = message;
        this.date = date;
    }

    public String getTitre() {
        return titre;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }
}
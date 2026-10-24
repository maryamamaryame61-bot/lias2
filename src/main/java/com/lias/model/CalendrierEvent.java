package com.lias.model;

public class CalendrierEvent {

    private String titre;
    private String date;
    private String description;

    public CalendrierEvent(String titre, String date, String description) {
        this.titre = titre;
        this.date = date;
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}

package com.lias.model;

public class Evenement {

    private String titre;
    private String type;
    private String date;

    public Evenement(String titre, String type, String date) {
        this.titre = titre;
        this.type = type;
        this.date = date;
    }

    public String getTitre() {
        return titre;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }
}
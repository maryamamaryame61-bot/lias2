package com.lias.model;

public class PV {

    private String reunion;
    private String date;
    private String ordreJour;

    public PV(String reunion, String date, String ordreJour) {
        this.reunion = reunion;
        this.date = date;
        this.ordreJour = ordreJour;
    }

    public String getReunion() {
        return reunion;
    }

    public String getDate() {
        return date;
    }

    public String getOrdreJour() {
        return ordreJour;
    }
}

package com.lias.model;

public class DocumentLabo {

    private String titre;
    private String type;
    private String date;
    private String lien;

    public DocumentLabo(String titre, String type, String date, String lien) {
        this.titre = titre;
        this.type = type;
        this.date = date;
        this.lien = lien;
    }

    public String getTitre() { return titre; }
    public String getType() { return type; }
    public String getDate() { return date; }
    public String getLien() { return lien; }
}
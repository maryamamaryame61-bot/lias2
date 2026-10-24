package com.lias.model;

public class Affiliation {

    private String membre;
    private String equipe;
    private String dateDebut;
    private String dateFin;

    public Affiliation(String membre, String equipe, String dateDebut, String dateFin) {
        this.membre = membre;
        this.equipe = equipe;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public String getMembre() { return membre; }
    public String getEquipe() { return equipe; }
    public String getDateDebut() { return dateDebut; }
    public String getDateFin() { return dateFin; }
}
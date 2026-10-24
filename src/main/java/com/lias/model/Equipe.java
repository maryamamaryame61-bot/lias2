package com.lias.model;

public class Equipe {

    private String nom;
    private String responsable;
    private String domaine;

    public Equipe(String nom, String responsable, String domaine) {
        this.nom = nom;
        this.responsable = responsable;
        this.domaine = domaine;
    }

    public String getNom() {
        return nom;
    }

    public String getResponsable() {
        return responsable;
    }

    public String getDomaine() {
        return domaine;
    }
}
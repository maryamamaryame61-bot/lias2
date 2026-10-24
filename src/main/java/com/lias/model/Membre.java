package com.lias.model;

public class Membre {

    private String nom;
    private String prenom;
    private String email;
    private String statut;

    public Membre(String nom, String prenom, String email, String statut) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.statut = statut;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getStatut() {
        return statut;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
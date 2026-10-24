package com.lias.model;

public class Publication {

    private String titre;
    private String auteur;
    private int annee;
    private String equipe;

    public Publication(String titre, String auteur, int annee, String equipe) {
        this.titre = titre;
        this.auteur = auteur;
        this.annee = annee;
        this.equipe = equipe;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public int getAnnee() {
        return annee;
    }

    public String getEquipe() {
        return equipe;
    }
}

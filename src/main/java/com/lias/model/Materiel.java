package com.lias.model;

public class Materiel {

    private String nom;
    private int quantite;
    private String dateArrivee;
    private String beneficiaire;

    public Materiel(String nom, int quantite, String dateArrivee, String beneficiaire) {
        this.nom = nom;
        this.quantite = quantite;
        this.dateArrivee = dateArrivee;
        this.beneficiaire = beneficiaire;
    }

    public String getNom() { return nom; }
    public int getQuantite() { return quantite; }
    public String getDateArrivee() { return dateArrivee; }
    public String getBeneficiaire() { return beneficiaire; }
}

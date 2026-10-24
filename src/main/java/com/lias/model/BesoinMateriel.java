package com.lias.model;

public class BesoinMateriel {

    private String demandeur;
    private String materiel;
    private int quantite;

    public BesoinMateriel(String demandeur, String materiel, int quantite) {
        this.demandeur = demandeur;
        this.materiel = materiel;
        this.quantite = quantite;
    }

    public String getDemandeur() {
        return demandeur;
    }

    public String getMateriel() {
        return materiel;
    }

    public int getQuantite() {
        return quantite;
    }
}

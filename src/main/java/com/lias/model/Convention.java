package com.lias.model;

public class Convention {

    private String partenaire;
    private String objet;
    private String dateSignature;
    private String duree;

    public Convention(String partenaire, String objet, String dateSignature, String duree) {
        this.partenaire = partenaire;
        this.objet = objet;
        this.dateSignature = dateSignature;
        this.duree = duree;
    }

    public String getPartenaire() { return partenaire; }
    public String getObjet() { return objet; }
    public String getDateSignature() { return dateSignature; }
    public String getDuree() { return duree; }
}

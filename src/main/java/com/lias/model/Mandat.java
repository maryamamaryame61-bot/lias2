package com.lias.model;

public class Mandat {

    private String directeur;
    private String viceDirecteur;
    private String chefEquipe;
    private String dateDebut;
    private String dateFin;

    public Mandat(String directeur, String viceDirecteur, String chefEquipe,
                  String dateDebut, String dateFin) {
        this.directeur = directeur;
        this.viceDirecteur = viceDirecteur;
        this.chefEquipe = chefEquipe;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public String getDirecteur() { return directeur; }
    public String getViceDirecteur() { return viceDirecteur; }
    public String getChefEquipe() { return chefEquipe; }
    public String getDateDebut() { return dateDebut; }
    public String getDateFin() { return dateFin; }
}

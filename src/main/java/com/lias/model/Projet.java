package com.lias.model;

public class Projet {

    private String titre;
    private String responsable;
    private String budget;

    public Projet(String titre, String responsable, String budget) {
        this.titre = titre;
        this.responsable = responsable;
        this.budget = budget;
    }

    public String getTitre() {
        return titre;
    }

    public String getResponsable() {
        return responsable;
    }

    public String getBudget() {
        return budget;
    }
}

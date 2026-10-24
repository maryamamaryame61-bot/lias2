package com.lias.model;

public class RapportAnnuel {

    private int annee;
    private String resume;

    public RapportAnnuel(int annee, String resume) {
        this.annee = annee;
        this.resume = resume;
    }

    public int getAnnee() {
        return annee;
    }

    public String getResume() {
        return resume;
    }
}

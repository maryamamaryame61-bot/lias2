package com.lias.model;

public class RoleResponsabilite {

    private String membre;
    private String role;
    private String dateDebut;

    public RoleResponsabilite(String membre, String role, String dateDebut) {
        this.membre = membre;
        this.role = role;
        this.dateDebut = dateDebut;
    }

    public String getMembre() { return membre; }
    public String getRole() { return role; }
    public String getDateDebut() { return dateDebut; }
}

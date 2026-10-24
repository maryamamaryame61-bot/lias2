package com.lias.model;

public class DemandeAdhesion {

    private String nom;
    private String email;
    private String motivation;
    private String statut;

    public DemandeAdhesion(String nom, String email, String motivation) {
        this.nom = nom;
        this.email = email;
        this.motivation = motivation;
        this.statut = "En attente";
    }

    public String getNom() { return nom; }
    public String getEmail() { return email; }
    public String getMotivation() { return motivation; }
    public String getStatut() { return statut; }
    public void accepter() { this.statut = "Acceptée"; }
    public void refuser() { this.statut = "Refusée"; }
  
    }

   
    
    


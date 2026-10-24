package com.lias.model;

public class MessageInterne {

    private String expediteur;
    private String destinataire;
    private String contenu;

    public MessageInterne(String expediteur, String destinataire, String contenu) {
        this.expediteur = expediteur;
        this.destinataire = destinataire;
        this.contenu = contenu;
    }

    public String getExpediteur() {
        return expediteur;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public String getContenu() {
        return contenu;
    }
}

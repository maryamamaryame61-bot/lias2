package com.lias.data;

import java.util.ArrayList;
import com.lias.model.DemandeAdhesion;

public class BaseDemandes {

    private static ArrayList<DemandeAdhesion> demandes = new ArrayList<>();

    public static void ajouter(DemandeAdhesion d) {
        demandes.add(d);
    }

    public static ArrayList<DemandeAdhesion> getDemandes() {
        return demandes;
    }
    public static DemandeAdhesion getDemande(int index) {
        return demandes.get(index);
    }
    
    
}

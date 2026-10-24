package com.lias.data;

import java.util.ArrayList;
import com.lias.model.Utilisateur;

public class BaseUtilisateurs {
    private static ArrayList<Utilisateur> utilisateurs = new ArrayList<>();

    public static void ajouter(Utilisateur u) {
        utilisateurs.add(u);
    }

    public static ArrayList<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }
}

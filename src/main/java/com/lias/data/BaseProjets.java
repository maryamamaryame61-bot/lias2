package com.lias.data;

import java.util.ArrayList;
import com.lias.model.Projet;

public class BaseProjets {

    private static ArrayList<Projet> projets =
            new ArrayList<>();

    public static void ajouter(Projet p) {
        projets.add(p);
    }

    public static ArrayList<Projet> getProjets() {
        return projets;
    }
}
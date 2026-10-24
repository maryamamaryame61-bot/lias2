package com.lias.data;

import java.util.ArrayList;
import com.lias.model.Evenement;

public class BaseEvenements {

    private static ArrayList<Evenement> evenements = new ArrayList<>();

    public static void ajouter(Evenement e) {
        evenements.add(e);
    }

    public static ArrayList<Evenement> getEvenements() {
        return evenements;
    }
}

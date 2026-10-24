package com.lias.data;

import java.util.ArrayList;
import com.lias.model.BesoinMateriel;

public class BaseBesoins {

    private static ArrayList<BesoinMateriel> besoins = new ArrayList<>();

    public static void ajouter(BesoinMateriel b) {
        besoins.add(b);
    }

    public static ArrayList<BesoinMateriel> getBesoins() {
        return besoins;
    }
}
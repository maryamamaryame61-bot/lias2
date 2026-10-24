package com.lias.data;

import java.util.ArrayList;
import com.lias.model.Materiel;

public class BaseMateriels {

    private static ArrayList<Materiel> materiels = new ArrayList<>();

    public static void ajouter(Materiel m) {
        materiels.add(m);
    }

    public static ArrayList<Materiel> getMateriels() {
        return materiels;
    }
}
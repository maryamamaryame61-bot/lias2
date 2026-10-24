package com.lias.data;

import java.util.ArrayList;
import com.lias.model.Mandat;

public class BaseMandats {

    private static ArrayList<Mandat> mandats = new ArrayList<>();

    public static void ajouter(Mandat m) {
        mandats.add(m);
    }

    public static ArrayList<Mandat> getMandats() {
        return mandats;
    }
}

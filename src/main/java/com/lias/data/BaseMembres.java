package com.lias.data;

import java.util.ArrayList;
import com.lias.model.Membre;

public class BaseMembres {

    private static ArrayList<Membre> membres = new ArrayList<>();

    public static void ajouter(Membre m) {
        membres.add(m);
    }

    public static ArrayList<Membre> getMembres() {
        return membres;
    }
}
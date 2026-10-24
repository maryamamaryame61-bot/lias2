package com.lias.data;

import java.util.ArrayList;
import com.lias.model.Equipe;

public class BaseEquipes {

    private static ArrayList<Equipe> equipes =
            new ArrayList<>();

    public static void ajouter(Equipe e) {
        equipes.add(e);
    }

    public static ArrayList<Equipe> getEquipes() {
        return equipes;
    }
}
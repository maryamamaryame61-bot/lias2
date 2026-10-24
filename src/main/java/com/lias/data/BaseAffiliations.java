package com.lias.data;

import java.util.ArrayList;
import com.lias.model.Affiliation;

public class BaseAffiliations {

    private static ArrayList<Affiliation> affiliations = new ArrayList<>();

    public static void ajouter(Affiliation a) {
        affiliations.add(a);
    }

    public static ArrayList<Affiliation> getAffiliations() {
        return affiliations;
    }
}

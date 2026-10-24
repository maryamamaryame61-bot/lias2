package com.lias.data;

import java.util.ArrayList;
import com.lias.model.RapportAnnuel;

public class BaseRapports {

    private static ArrayList<RapportAnnuel> rapports =
            new ArrayList<>();

    public static void ajouter(RapportAnnuel r) {
        rapports.add(r);
    }

    public static ArrayList<RapportAnnuel> getRapports() {
        return rapports;
    }
}

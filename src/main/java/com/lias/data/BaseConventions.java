package com.lias.data;

import java.util.ArrayList;
import com.lias.model.Convention;

public class BaseConventions {

    private static ArrayList<Convention> conventions = new ArrayList<>();

    public static void ajouter(Convention c) {
        conventions.add(c);
    }

    public static ArrayList<Convention> getConventions() {
        return conventions;
    }
}

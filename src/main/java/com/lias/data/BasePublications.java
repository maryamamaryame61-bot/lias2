package com.lias.data;

import java.util.ArrayList;
import com.lias.model.Publication;

public class BasePublications {

    private static ArrayList<Publication> publications = new ArrayList<>();

    public static void ajouter(Publication p) {
        publications.add(p);
    }

    public static ArrayList<Publication> getPublications() {
        return publications;
    }
}

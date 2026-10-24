package com.lias.data;

import java.util.ArrayList;
import com.lias.model.DocumentLabo;

public class BaseDocuments {

    private static ArrayList<DocumentLabo> documents = new ArrayList<>();

    public static void ajouter(DocumentLabo d) {
        documents.add(d);
    }

    public static ArrayList<DocumentLabo> getDocuments() {
        return documents;
    }
}
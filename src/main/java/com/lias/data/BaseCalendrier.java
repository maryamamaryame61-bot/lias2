package com.lias.data;

import java.util.ArrayList;
import com.lias.model.CalendrierEvent;

public class BaseCalendrier {

    private static ArrayList<CalendrierEvent> events = new ArrayList<>();

    public static void ajouter(CalendrierEvent e) {
        events.add(e);
    }

    public static ArrayList<CalendrierEvent> getEvents() {
        return events;
    }
}

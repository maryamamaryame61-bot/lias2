package com.lias.data;

import java.util.ArrayList;
import com.lias.model.Notification;

public class BaseNotifications {

    private static ArrayList<Notification> notifications = new ArrayList<>();

    public static void ajouter(Notification n) {
        notifications.add(n);
    }

    public static ArrayList<Notification> getNotifications() {
        return notifications;
    }
}

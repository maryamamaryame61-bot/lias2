package com.lias.data;

import java.util.ArrayList;
import com.lias.model.MessageInterne;

public class BaseMessages {

    private static ArrayList<MessageInterne> messages = new ArrayList<>();

    public static void ajouter(MessageInterne m) {
        messages.add(m);
    }

    public static ArrayList<MessageInterne> getMessages() {
        return messages;
    }
}

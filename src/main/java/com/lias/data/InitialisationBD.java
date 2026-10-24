package com.lias.data;

import java.sql.Connection;
import java.sql.Statement;

public class InitialisationBD {

    public static void creerTables() {
        try {
            Connection con = ConnexionSQLite.getConnection();
            if (con == null) return;

            Statement st = con.createStatement();

            st.executeUpdate(
                "CREATE TABLE IF NOT EXISTS membre (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nom TEXT," +
                "prenom TEXT," +
                "email TEXT UNIQUE," +
                "statut TEXT)"
            );

            st.executeUpdate(
                "CREATE TABLE IF NOT EXISTS publication (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titre TEXT," +
                "auteur TEXT," +
                "date TEXT)"
            );

            st.executeUpdate(
                "CREATE TABLE IF NOT EXISTS document (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titre TEXT," +
                "type TEXT," +
                "date TEXT," +
                "lien TEXT)"
            );

            st.executeUpdate(
                "CREATE TABLE IF NOT EXISTS evenement (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titre TEXT," +
                "type TEXT," +
                "date TEXT," +
                "lieu TEXT)"
            );

            st.executeUpdate(
                "CREATE TABLE IF NOT EXISTS utilisateur (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "login TEXT UNIQUE," +
                "password TEXT," +
                "role TEXT)"
            );

            // Compte administrateur par défaut (admin / admin123)
            try {
                st.executeUpdate("INSERT OR IGNORE INTO utilisateur(id, login, password, role) VALUES(1, 'admin', 'admin123', 'admin')");
            } catch (Exception ignored) {}

            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

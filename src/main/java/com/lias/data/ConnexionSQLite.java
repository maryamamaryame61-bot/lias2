package com.lias.data;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexionSQLite {

	private static final String URL =
	        "jdbc:sqlite:lias.db"; 
	
    public static Connection getConnection() {

        try {

            Class.forName("org.sqlite.JDBC");

            return DriverManager.getConnection(URL);

        } catch (Exception e) {
            System.out.println("ERREUR SQLITE");
            e.printStackTrace();
            return null;
        }
    }
}

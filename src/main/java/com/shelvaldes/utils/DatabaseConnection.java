package com.shelvaldes.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Singleton: Sólo una instancia de conexión en todo el programa
public class DatabaseConnection {

    private static String url = "jdbc:mariadb://localhost:3306/javacursodb";
    private static String user = "admin";
    private static String pass = "1234";
    private static Connection myConn;

    public static Connection getInstance() throws SQLException {
        if (myConn == null) {
            myConn = DriverManager.getConnection(url, user, pass);
        }
        return myConn;
    }

}

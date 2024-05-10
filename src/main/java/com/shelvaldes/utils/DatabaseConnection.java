package com.shelvaldes.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Singleton: Sólo una instancia de conexión en todo el programa
public class DatabaseConnection {

    private static String url = "jdbc:mariadb://localhost:3306/javacursodb";
    private static String user = "admin";
    private static String pass = "1234";
//    private static Connection myConn;
    private static BasicDataSource pool;

    public static BasicDataSource getInstance() throws SQLException {

        if (pool == null){
            pool = new BasicDataSource();
            pool.setUrl(url);
            pool.setUsername(user);
            pool.setPassword(pass);
            pool.setInitialSize(3); //Tamaño inicial del pool de conexiones
            pool.setMinIdle(3); // número mínimo de conexiones inactivas
            pool.setMaxIdle(10); // número máximo de conexiones inactivas
            pool.setMaxTotal(10); // máximo número de conexiones activas
        }
        return pool;
    }
    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }
}

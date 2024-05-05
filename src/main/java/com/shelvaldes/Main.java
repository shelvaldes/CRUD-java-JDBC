package com.shelvaldes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mariadb://localhost:3306/javacursodb";
        String user = "admin";
        String pass = "1234";

        try (
                Connection myCon = DriverManager.getConnection( url, user, pass);
                Statement myStmt = myCon.createStatement();
                ResultSet myRes = myStmt.executeQuery("SELECT * FROM employees");

                ){

            System.out.println("Conectado a la base de datos");

            while (myRes.next()) {
                System.out.println(myRes.getString("first_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Changos");
        }
    }
}
package com.shelvaldes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Connection myCon = null;
        Statement myStamt = null;
        ResultSet myRes = null;

        try {
            myCon = DriverManager.getConnection("jdbc:mariadb://localhost:3306/javacursodb", "admin", "1234");
            System.out.println("Conectado a la base de datos");
            myStamt = myCon.createStatement();
            myRes = myStamt.executeQuery("SELECT * FROM employees");
            while (myRes.next()) {
                System.out.println(myRes.getString("first_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Changos");
        }
    }
}
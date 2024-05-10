package com.shelvaldes.main;

import com.shelvaldes.models.Employee;
import com.shelvaldes.repository.EmployeeRepository;
import com.shelvaldes.repository.Repository;
import com.shelvaldes.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {

//        SwingApp app  = new SwingApp();
//        app.setVisible(true);

        System.out.println("--listando--");
        Repository<Employee> repository = new EmployeeRepository();
        repository.findAll().forEach(System.out::println);

        System.out.println("--Buscar por id--");
        System.out.println(repository.getById(7));

    }
}
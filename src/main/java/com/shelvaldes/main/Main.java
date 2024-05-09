package com.shelvaldes.main;

import com.shelvaldes.models.Employee;
import com.shelvaldes.repository.EmployeeRepository;
import com.shelvaldes.repository.Repository;
import com.shelvaldes.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        try ( Connection myConn = DatabaseConnection.getInstance()){
            Repository<Employee> repository = new EmployeeRepository();

            System.out.println("--Listando--");
            repository.findAll().forEach(System.out::println);


            System.out.println("--Empleado insertado--");
            repository.findAll().forEach(System.out::println);

            repository.delete(10);

            System.out.println("Empleado eliminado");


            System.out.println("--Listando después de actualizar--");
            repository.findAll().forEach(System.out::println);

        }
    }
}
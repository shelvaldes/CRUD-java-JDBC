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

            System.out.println("--Insertando un empleado--");
            Employee employee = new Employee();
            employee.setFirst_name("Celilio");
            employee.setPa_surname("Valdés");
            employee.setMa_surname("Herrera");
            employee.setEmail("gato@shelvaldes.com");
            employee.setSalary(70000F);
            repository.save(employee);

            System.out.println("--Nuevo empleado insertado--");
            repository.findAll().forEach(System.out::println);
        }
    }
}
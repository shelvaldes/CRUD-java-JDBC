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
            Employee employee1 = new Employee();
            employee1.setId(null);
            employee1.setFirst_name("Padme");
            employee1.setPa_surname("Hernández");
            employee1.setMa_surname("Valdés");
            employee1.setEmail("queso@shelvaldes.com");
            employee1.setSalary(60000F);
            repository.save(employee1);

            System.out.println("--Empleado insertado--");
            repository.findAll().forEach(System.out::println);

            //Aquí quiero hardcodear un update
            Employee employee2 = new Employee();
            employee2.setId(7);
            employee2.setFirst_name("Cecilio");
            employee2.setPa_surname("Valdés");
            employee2.setMa_surname("morán");
            employee2.setEmail("gato@shelvaldes.com");
            employee2.setSalary(80000F);
            repository.save(employee2);

            System.out.println("Empleado actualizado");


            System.out.println("--Listando después de actualizar--");
            repository.findAll().forEach(System.out::println);

        }
    }
}
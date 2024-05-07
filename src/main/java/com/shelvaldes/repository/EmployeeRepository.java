package com.shelvaldes.repository;

import com.shelvaldes.models.Employee;
import com.shelvaldes.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee> {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (
                Statement myStmt = getConnection().createStatement();
                ResultSet myRes = myStmt.executeQuery("SELECT * FROM employees")
        ) {
            while (myRes.next()){
                Employee e = createEmployee(myRes);
                employees.add(e);
            }
        }
        return employees;
    }

    @Override
    public Employee getById(Integer id) throws SQLException {
        Employee employee = null;
        try (PreparedStatement myStmt = getConnection().prepareStatement("SELECT * FROM employees WHERE id = ?")){
            myStmt.setInt(1,id);
            try (ResultSet myRes = myStmt.executeQuery()){
                if (myRes.next()){
                    employee = createEmployee(myRes);
                }
            }
        }
        return employee;
    }

    @Override
    public void save(Employee employee) throws SQLException {
        String sql = "INSERT INTO employees (first_name, pa_surname, ma_surname, email, salary) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement myStmt = getConnection().prepareStatement(sql)) {
            myStmt.setString(1, employee.getFirst_name());
            myStmt.setString(2, employee.getPa_surname());
            myStmt.setString(3, employee.getMa_surname());
            myStmt.setString(4, employee.getEmail());
            myStmt.setFloat(5, employee.getSalary());
            myStmt.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) {

    }

    private Employee createEmployee(ResultSet myRes) throws SQLException {
        Employee e = new Employee();
        e.setId(myRes.getInt("id"));
        e.setFirst_name(myRes.getString("first_name"));
        e.setPa_surname(myRes.getString("pa_surname"));
        e.setMa_surname(myRes.getString("ma_surname"));
        e.setEmail(myRes.getString("email"));
        e.setSalary(myRes.getFloat("salary"));
        return e;
    }
}

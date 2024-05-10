package com.shelvaldes.repository;

import com.shelvaldes.models.Employee;
import com.shelvaldes.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee> {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnection();
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (
                Connection myConn = getConnection();
                Statement myStmt = myConn.createStatement();
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
        try (
                Connection myConn = getConnection();
                PreparedStatement myStmt = myConn.prepareStatement("SELECT * FROM employees WHERE id = ?")
        ){
            myStmt.setInt(1,id);
            try (ResultSet myRes = myStmt.executeQuery()){
                if (myRes.next()){
                    employee = createEmployee(myRes);
                }
            }
        }
        return employee;
    }

    //meter las referencias a curp donde haga falta
    @Override
    public void save(Employee employee) throws SQLException {
        String sql;
        if (employee.getId() != null && employee.getId()>0 ){
            sql = "UPDATE employees SET first_name = ?, pa_surname = ?, ma_surname = ?, email = ?, salary = ?, curp = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO employees (first_name, pa_surname, ma_surname, email, salary, curp) VALUES (?, ?, ?, ?, ?, ?)";
        }
        try (
                Connection myConn = getConnection();
                PreparedStatement myStmt = myConn.prepareStatement(sql)
        ) {
            myStmt.setString(1, employee.getFirst_name());
            myStmt.setString(2, employee.getPa_surname());
            myStmt.setString(3, employee.getMa_surname());
            myStmt.setString(4, employee.getEmail());
            myStmt.setFloat(5, employee.getSalary());
            myStmt.setString(6, employee.getCurp());
            if (employee.getId() != null && employee.getId()>0){
                myStmt.setInt(7, employee.getId());
            }
            myStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {

        try (
                Connection myConn = getConnection();
                PreparedStatement myStmt = myConn.prepareStatement("DELETE FROM employees WHERE id = ?")
        ){
            myStmt.setInt(1, id);
            myStmt.executeUpdate();
        }

    }

    private Employee createEmployee(ResultSet myRes) throws SQLException {
        Employee e = new Employee();
        e.setId(myRes.getInt("id"));
        e.setFirst_name(myRes.getString("first_name"));
        e.setPa_surname(myRes.getString("pa_surname"));
        e.setMa_surname(myRes.getString("ma_surname"));
        e.setEmail(myRes.getString("email"));
        e.setSalary(myRes.getFloat("salary"));
        e.setCurp(myRes.getString("curp"));
        return e;
    }
}

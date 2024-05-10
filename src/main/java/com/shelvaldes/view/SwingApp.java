package com.shelvaldes.view;

import com.shelvaldes.models.Employee;
import com.shelvaldes.repository.EmployeeRepository;
import com.shelvaldes.repository.Repository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class SwingApp extends JFrame {
    private final Repository<Employee> employeeRepository;
    private final JTable employeeTable;

    public SwingApp (){
        //Configurar la ventana
        setTitle("Gestión de Empleados");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 230);

        //Crear tabla
        employeeTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        //Crear botones
        JButton agregarBtn = new JButton("Agregar");
        JButton actualizarBtn = new JButton("Actualizar");
        JButton eliminarBtn = new JButton("Eliminar");

        //configurar panel de botones
        JPanel btnPanel = new JPanel();
        btnPanel.add(agregarBtn);
        btnPanel.add(actualizarBtn);
        btnPanel.add(eliminarBtn);
        add(btnPanel, BorderLayout.SOUTH);

        //Estilos
        //foo

        //Crear el objeto repository para acceder a la bd
        employeeRepository = new EmployeeRepository();

        //Cargar los empleados iniciales en la tabla
        refreshEmployeeTable();

        //Agregar Action Listener para los botones
        agregarBtn.addActionListener(e ->{
            try {
                agregarEmpleado();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        actualizarBtn.addActionListener( e -> actualizarEmpleado());
        eliminarBtn.addActionListener( e -> eliminarEmpleado());
    }

    private void refreshEmployeeTable() {
        //Obtener lista de empleados
        try {
            List<Employee> employees = employeeRepository.findAll();

            //Crear un modelo de tabla y establecer l0s datos de los empleados
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Nombre");
            model.addColumn("Primer Apellido");
            model.addColumn("Segundo Apellido");
            model.addColumn("email");
            model.addColumn("Salario");
            model.addColumn("CURP");

            for (Employee employee : employees) {
                Object[] rowData = {
                        employee.getId(),
                        employee.getFirst_name(),
                        employee.getPa_surname(),
                        employee.getMa_surname(),
                        employee.getEmail(),
                        employee.getSalary(),
                        employee.getCurp()
                };
                model.addRow(rowData);
            }

            //Establecer el modelo de tabla actualizado
            employeeTable.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener los datos");
        }
    }

    private void agregarEmpleado() throws SQLException {
        //crear formulario
        JTextField nombreField = new JTextField();
        JTextField primerField = new JTextField();
        JTextField segundoField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField salarioField = new JTextField();
        JTextField curpField = new JTextField();

        Object[] fields = {
                "Nombre:", nombreField,
                "Primer Apellido:", primerField,
                "Segundo Apellido;", segundoField,
                "Email:", emailField,
                "Salario:", salarioField,
                "CURP:", curpField
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "Agregar Empleado", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            // Crear un nuevo objeto Employee con los datos ingresados
            Employee employee = new Employee();
            employee.setFirst_name(nombreField.getText());
            employee.setPa_surname(primerField.getText());
            employee.setMa_surname(segundoField.getText());
            employee.setEmail(emailField.getText());
            employee.setSalary(Float.parseFloat(salarioField.getText()));
            employee.setCurp(curpField.getText());

            // Guardar el nuevo empleado en la base de datos
            employeeRepository.save(employee);

            // Actualizar la tabla con los empleados actualizados
            refreshEmployeeTable();

            JOptionPane.showMessageDialog(this, "Empleado agregado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void actualizarEmpleado() {
        //obtener id
        String empleadoIdStr = JOptionPane.showInputDialog(this, "Ingrese ID del empleado", "Actualizar empleado", JOptionPane.QUESTION_MESSAGE);
        if (empleadoIdStr != null) {
            try {
                int empleadoId = Integer.parseInt((empleadoIdStr));

                //obtener empleado desde la bd
                Employee empleado = employeeRepository.getById(empleadoId);

                if ( empleado != null) {
                    //Crear un formulario con los datos del empleado
                    JTextField nombreField = new JTextField(empleado.getFirst_name());
                    JTextField primerField = new JTextField(empleado.getPa_surname());
                    JTextField segundoField = new JTextField(empleado.getMa_surname());
                    JTextField emailField = new JTextField(empleado.getEmail());
                    JTextField salarioField = new JTextField(String.valueOf(empleado.getSalary()));
                    JTextField curpField = new JTextField(empleado.getCurp());

                    Object[] fields = {
                            "Nombre:", nombreField,
                            "Primer Apellido:", primerField,
                            "Segundo Apellido;", segundoField,
                            "Email:", emailField,
                            "Salario:", salarioField,
                            "CURP:", curpField
                    };

                    int confirmResult = JOptionPane.showConfirmDialog(this, fields, "Actualizar empleado", JOptionPane.OK_CANCEL_OPTION);
                    if (confirmResult == JOptionPane.OK_OPTION){
                        //Actualizar con valores del form
                        empleado.setFirst_name(nombreField.getText());
                        empleado.setPa_surname(primerField.getText());
                        empleado.setMa_surname(segundoField.getText());
                        empleado.setEmail(emailField.getText());
                        empleado.setSalary(Float.parseFloat(salarioField.getText()));
                        empleado.setCurp(curpField.getText());

                        //Guardar cambios en bd
                        employeeRepository.save(empleado);

                        //Actualizar la tabla en interfaz
                        refreshEmployeeTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Ese empleado no existe", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al obtener los datos de empleado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarEmpleado() {
        //Obtener Id del empleado a eliminar
        String empleadoIdStr = JOptionPane.showInputDialog(this, "Ingreseel ID del empleado", "Eliminar empleado", JOptionPane.QUESTION_MESSAGE);
        if (empleadoIdStr != null) {
            try {
                int empleadoId = Integer.parseInt(empleadoIdStr);

                //confirmar eliminación
                int confirmResult = JOptionPane.showConfirmDialog(this, "Está seguro de eliminar el empleado?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirmResult == JOptionPane.YES_OPTION) {
                    //Eliminar empleado de bd
                    employeeRepository.delete(empleadoId);
                    //Actualizar la interfaz
                    refreshEmployeeTable();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                throw new RuntimeException();
            }
        }
    }
}
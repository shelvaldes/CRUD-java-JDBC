package com.shelvaldes.repository;

import com.shelvaldes.models.Employee;

import java.sql.SQLException;
import java.util.List;

// se le asigna un tipo genérico para flexibilidad
public interface Repository<T> {

    List<T> findAll() throws SQLException;
    T getById(Integer id) throws SQLException;
    void save(T t);
    void delete(Integer id);

}

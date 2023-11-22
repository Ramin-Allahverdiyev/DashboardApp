package com.dashboard.dao;

import com.dashboard.model.Employees;

import java.util.List;

public interface UserDao {
    void register(Employees employees);
    Employees getUser(String email, String password);

    List<Employees> selectAllEmployees(String email);
    List<Employees> selectAllEmployees();

    List<Employees> percentageOfDep();

    List<Employees> filteredData(String name, String department , String gender);
    List<Employees> filteredDataWithoutName(String department , String gender);

    Employees getEmployee(int id);
    int numberOfEmployees();

    void updateEmployee(Employees employees);
}

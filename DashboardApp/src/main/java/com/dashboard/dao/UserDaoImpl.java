package com.dashboard.dao;

import com.dashboard.model.Employees;
import com.dashboard.utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDaoImpl implements UserDao{
    @Override
    public void register(Employees employees) {
        String USER_INSERT_SQL = "INSERT INTO Employees(first_name, last_name, email, gender,password,department,age) values (?, ?, ?, ?,?,?,?);";

        try {
            Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(USER_INSERT_SQL);
            statement.setString(1, employees.getName());
            statement.setString(2, employees.getSurname());
            statement.setString(3, employees.getEmail());
            statement.setString(4, employees.getGender());
            statement.setString(5, employees.getPassword());
            statement.setString(6, employees.getDepartment());
            statement.setString(7, Integer.toString(employees.getAge()));

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Employees getUser(String email, String password) {
        String GET_USER_SQL = "SELECT id, first_name, last_name, email, gender,password,department,age FROM Employees WHERE email = ? AND password = ?;";
        Employees employees = null;

        try {
            Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_USER_SQL);
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                employees = new Employees();
                employees.setId(resultSet.getInt("id"));
                employees.setName(resultSet.getString("first_name"));
                employees.setSurname(resultSet.getString("last_name"));
                employees.setEmail(resultSet.getString("email"));
                employees.setPassword(resultSet.getString("password"));
                employees.setGender(resultSet.getString("gender"));
                employees.setAge(resultSet.getInt("age"));
                employees.setDepartment(resultSet.getString("department"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return employees;
    }

    @Override
    public List<Employees> selectAllEmployees(String email) {
        String SELECT_ALL_TODOS_SQL = "SELECT id, first_name, last_name, email, gender,password,department,age FROM Employees WHERE email = ?";

        List<Employees> emps = new ArrayList<>();
        try {
            Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_TODOS_SQL);
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Employees employees = new Employees();
                employees.setId(resultSet.getInt("id"));
                employees.setName(resultSet.getString("first_name"));
                employees.setSurname(resultSet.getString("last_name"));
                employees.setEmail(resultSet.getString("email"));
                employees.setPassword(resultSet.getString("password"));
                employees.setGender(resultSet.getString("gender"));
                employees.setAge(resultSet.getInt("age"));
                employees.setDepartment(resultSet.getString("department"));

                emps.add(employees);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return emps;
    }

    @Override
    public List<Employees> selectAllEmployees() {
        String SELECT_ALL_TODOS_SQL = "SELECT id, first_name, last_name, email, gender,password,department,age FROM Employees where id>0;";

        List<Employees> empls = new ArrayList<>();
        try {
            Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_TODOS_SQL);


            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Employees employees = new Employees();
                employees.setId(resultSet.getInt("id"));
                employees.setName(resultSet.getString("first_name"));
                employees.setSurname(resultSet.getString("last_name"));
                employees.setEmail(resultSet.getString("email"));
                employees.setPassword(resultSet.getString("password"));
                employees.setGender(resultSet.getString("gender"));
                employees.setAge(resultSet.getInt("age"));
                employees.setDepartment(resultSet.getString("department"));

                empls.add(employees);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return empls;
    }

    @Override
    public List<Employees> percentageOfDep() {
        List<Employees> empls=new ArrayList<>();
        try {
            Connection connection = ConnectionUtil.getConnection();

            List<String> departments=new ArrayList<>();
            departments.add("IT");
            departments.add("HR");
            departments.add("Engineering");
            departments.add("Accounting");
            Iterator<String> iterator=departments.iterator();
            while (iterator.hasNext()){
                Employees employees = new Employees();
                String dep=iterator.next();
                String SQL_STATISTIC = "SELECT  ROUND((SELECT COUNT(*) FROM Employees where department = ? ) / (COUNT(*)) * 100, 3) AS percentage, (SELECT COUNT(*) FROM Employees where id > 0) AS allemp , (SELECT COUNT(*) FROM Employees where department = ? ) AS numdepemp FROM Employees;";
                PreparedStatement statement2 = connection.prepareStatement(SQL_STATISTIC);
                statement2.setString(1,dep);
                statement2.setString(2,dep);
                ResultSet resultSet2 = statement2.executeQuery();
                if(resultSet2.next()){
                    double percentage=resultSet2.getInt("percentage");
                    int allemp=resultSet2.getInt("allemp");
                    int numdepemp=resultSet2.getInt("numdepemp");
                    employees.setDepartment(dep);
                    employees.setPercentageOfDep(percentage);
                    employees.setNumdepemp(numdepemp);
                    employees.setAllemp(allemp);
                    empls.add(employees);
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return empls;
    }

    @Override
    public List<Employees> filteredData(String name, String department, String gender) {
        String SELECT_ALL_TODOS_SQL = "SELECT id, first_name, last_name, email, gender,password,department,age FROM Employees where name like ? and department = ? and gender = ? ";

        List<Employees> empls = new ArrayList<>();
        try {
            Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_TODOS_SQL);
            statement.setString(1, "%"+name+"%");
            statement.setString(2, department);
            statement.setString(3, gender);


            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Employees employees = new Employees();
                employees.setId(resultSet.getInt("id"));
                employees.setName(resultSet.getString("first_name"));
                employees.setSurname(resultSet.getString("last_name"));
                employees.setEmail(resultSet.getString("email"));
                employees.setPassword(resultSet.getString("password"));
                employees.setGender(resultSet.getString("gender"));
                employees.setAge(resultSet.getInt("age"));
                employees.setDepartment(resultSet.getString("department"));

                empls.add(employees);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return empls;
    }

    @Override
    public List<Employees> filteredDataWithoutName(String department, String gender) {
        String SELECT_ALL_TODOS_SQL = "SELECT id, first_name, last_name, email, gender,password,department,age FROM Employees where department= ? and gender= ?";

        List<Employees> empls = new ArrayList<>();
        try {
            Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_TODOS_SQL);
            statement.setString(1, department);
            statement.setString(2, gender);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Employees employees = new Employees();
                employees.setId(resultSet.getInt("id"));
                employees.setName(resultSet.getString("first_name"));
                employees.setSurname(resultSet.getString("last_name"));
                employees.setEmail(resultSet.getString("email"));
                employees.setPassword(resultSet.getString("password"));
                employees.setGender(resultSet.getString("gender"));
                employees.setAge(resultSet.getInt("age"));
                employees.setDepartment(resultSet.getString("department"));

                empls.add(employees);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return empls;
    }

    @Override
    public Employees getEmployee(int id) {
        String GET_ARTICLE_SQL = "SELECT id, first_name, last_name, email, gender,password,department,age FROM Employees WHERE id = ?";

        Employees employees = null;

        try {
            Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ARTICLE_SQL);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                employees = new Employees();
                employees.setId(resultSet.getInt("id"));
                employees.setName(resultSet.getString("first_name"));
                employees.setSurname(resultSet.getString("last_name"));
                employees.setEmail(resultSet.getString("email"));
                employees.setPassword(resultSet.getString("password"));
                employees.setGender(resultSet.getString("gender"));
                employees.setAge(resultSet.getInt("age"));
                employees.setDepartment(resultSet.getString("department"));

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return employees;
    }

    @Override
    public int numberOfEmployees() {
        String SQL_STATISTIC = "SELECT (SELECT COUNT(*) FROM Employees where id>0) AS numofemp from Employees; ";

        try {
            Connection connection=ConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_STATISTIC);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int numofemp = resultSet.getInt("numofemp");
                return numofemp;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    @Override
    public void updateEmployee(Employees employees) {
        String UPDATE_ARTICLE_SQL = "UPDATE Employees SET first_name = ?, last_name = ?, email = ?, gender = ?,department = ?,age = ?, password = ? WHERE id = ?;";

        try {
            Connection connection = ConnectionUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_ARTICLE_SQL);
            statement.setString(1, employees.getName());
            statement.setString(2, employees.getSurname());
            statement.setString(3, employees.getEmail());
            statement.setString(4, employees.getGender());
            statement.setString(5, employees.getDepartment());
            statement.setInt(6, employees.getAge());
            statement.setString(7, employees.getPassword());
            statement.setInt(8,employees.getId());

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

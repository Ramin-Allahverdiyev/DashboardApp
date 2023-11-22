package com.dashboard.model;

public class Employees {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String gender;
    private String department;
    private int age;
    private int allemp;
    private int numdepemp;

    public int getAllemp() {
        return allemp;
    }

    public void setAllemp(int allemp) {
        this.allemp = allemp;
    }

    public int getNumdepemp() {
        return numdepemp;
    }

    public void setNumdepemp(int numdepemp) {
        this.numdepemp = numdepemp;
    }

    private double percentageOfDep;
    public Employees() {
    }

    public double getPercentageOfDep() {
        return percentageOfDep;
    }

    public void setPercentageOfDep(double percentageOfDep) {
        this.percentageOfDep = percentageOfDep;
    }

    public Employees(int id, String name, String surname, String email, String password, String gender, String department, int age, int allemp, int numdepemp, double percentageOfDep) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.department = department;
        this.age = age;
        this.allemp = allemp;
        this.numdepemp = numdepemp;
        this.percentageOfDep = percentageOfDep;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

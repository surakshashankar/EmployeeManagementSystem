package com.sample.employmanagementsystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_details")
public class EmployeeDetails {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String email;
    private String phone;
    private Integer sal;
    private String userName;
    private String password;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Roles role;

    @ManyToOne
    @JoinColumn(name= "dept_id")
    private Departments departments;

    public EmployeeDetails() {
    }

    public EmployeeDetails(int id, String name, String email, String phone,String userName,String password, int sal,Roles role,Departments departments) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.departments = departments;
        this.sal = sal;
        this.userName = userName;
        this.password = password;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Departments getDepartments(Departments departments) {
        return this.departments;
    }

    public void setDepartments(Departments departments) {
        this.departments = departments;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSal() {
        return sal;
    }

    public void setSal(Integer sal) {
        this.sal = sal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Integer getDeptId() {
        return 0;
    }

    public String getUsername() {
      return userName;
    }
}
































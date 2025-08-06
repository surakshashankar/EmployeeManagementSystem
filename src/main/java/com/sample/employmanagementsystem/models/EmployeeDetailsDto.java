package com.sample.employmanagementsystem.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeDetailsDto {

    @NotNull(message = "Enter valid Employee ID")
    public Integer deptId;

    @Size(min = 2, max = 16,message= "Name should be between 2 to 16")
    private String name;

    @Email
    private String email;

    private String phone;

    private Integer sal;

    private Integer roleId;

    private String userName;

    private String password;

    public EmployeeDetailsDto() {
    }

    public EmployeeDetailsDto(String name, String email, String phone,String userName, String password,Integer sal, Integer roleId , Integer deptId) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.sal= sal;
        this.roleId = roleId;
        this.deptId = deptId;
        this.userName = userName;
        this.password = password;
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

    public Integer getSal() {
        return sal;
    }

    public void setSal(Integer sal) {
        this.sal = sal;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}

































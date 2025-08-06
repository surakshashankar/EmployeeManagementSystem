package com.sample.employmanagementsystem.models;


public class AuthRequest{

 private String userName;

 private String password;

  public AuthRequest(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return userName;
  }

  public String getPassword() {
    return password;
  }
  }

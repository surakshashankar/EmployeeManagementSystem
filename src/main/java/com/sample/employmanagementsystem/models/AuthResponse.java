package com.sample.employmanagementsystem.models;

public class AuthResponse {

  private String token;
  private String errorMessage;

  public AuthResponse() {
  }

  public AuthResponse(String token, String errorMessage) {
    this.token = token;
    this.errorMessage = errorMessage;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}

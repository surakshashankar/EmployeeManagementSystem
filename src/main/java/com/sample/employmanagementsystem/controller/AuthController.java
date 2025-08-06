package com.sample.employmanagementsystem.controller;

import com.sample.employmanagementsystem.ServiceImpl.CustomUserDetailsService;
import com.sample.employmanagementsystem.models.AuthRequest;
import com.sample.employmanagementsystem.models.AuthResponse;
import com.sample.employmanagementsystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

  @Autowired
  private CustomUserDetailsService customUserDetailsService;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  JwtUtil jwtUtil;

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {

    try {
      UserDetails userDetails = customUserDetailsService.loadUserByUsername(authRequest.getUsername());

      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      authRequest.getUsername(),
                      authRequest.getPassword()
              ));

      String token = jwtUtil.generateToken(userDetails.getUsername());
      AuthResponse authResponse = new AuthResponse();
      authResponse.setToken(token);

      return new ResponseEntity<>(authResponse, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(new AuthResponse(null, "Login failed: " + e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
  }
}

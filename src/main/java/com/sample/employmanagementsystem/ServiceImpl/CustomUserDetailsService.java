package com.sample.employmanagementsystem.ServiceImpl;

import com.sample.employmanagementsystem.entity.EmployeeDetails;
import com.sample.employmanagementsystem.repository.EmployeeDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
//public class CustomUserDetailsService {
//
//  @Autowired
//  private EmployeeDetailsRepository employeeDetailsRepository;
//
//  public UserDetails loadUserByUsername(String username) {
//    EmployeeDetails employeeDetails = employeeDetailsRepository.findByUserName(username);
//    return User.builder()
//            .username(employeeDetails.getUsername())
//            .password(employeeDetails.getPassword())
//            .roles(employeeDetails.getRole().getName()).build();
//  }

  @Service
  public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    EmployeeDetailsRepository employeeDetailsRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      var user = employeeDetailsRepository.findByUserName(username);

      if(user != null) {
        return User.builder().
                username(user.getUsername()).
                password(user.getPassword()).
                roles(user.getRole().getName()).
                build();
      } else  {
        throw new UsernameNotFoundException("User not found with username: " + username);
      }
    }
  }


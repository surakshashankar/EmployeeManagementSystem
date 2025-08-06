package com.sample.employmanagementsystem.Service;

import com.sample.employmanagementsystem.entity.Departments;
import com.sample.employmanagementsystem.entity.EmployeeDetails;
import com.sample.employmanagementsystem.entity.Roles;
import com.sample.employmanagementsystem.models.EmployeeDetailsDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    public EmployeeDetails addEmployeeDetails(EmployeeDetailsDto employeeDetailsDto);
    
    //public String deleteEmployeeDetails(EmployeeDetailsDto employeeDetailsDto);

    String deleteEmployeeDetails(Integer id);

    String updateEmployeeDetails(Integer id, EmployeeDetails employeeDetails);

    //Roles getrolesName(Integer id);

    Roles getrolesName(Integer id);

    String addRolesName(Roles roles);

    String addDepartments(Departments departments);

    String getEmployeeNameById(Integer id);

    EmployeeDetails getemployeeDetails(Integer id);

   // EmployeeDetails getemployeeDetails(String userName);

    List<EmployeeDetails> getAllemployeeDetails();

    List<EmployeeDetails> getemployeeDetailsBy(Integer roleId);

//    List<EmployeeDetails> getemployeeDetailsByUsername(String userName);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}

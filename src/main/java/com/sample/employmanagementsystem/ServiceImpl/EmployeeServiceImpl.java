package com.sample.employmanagementsystem.ServiceImpl;

import com.sample.employmanagementsystem.Service.EmployeeService;
import com.sample.employmanagementsystem.entity.Departments;
import com.sample.employmanagementsystem.entity.EmployeeDetails;
import com.sample.employmanagementsystem.entity.Roles;
import com.sample.employmanagementsystem.exception.EmployeeNotFoundException;
import com.sample.employmanagementsystem.exception.InvalidDataInputException;
import com.sample.employmanagementsystem.models.EmployeeDetailsDto;
import com.sample.employmanagementsystem.repository.DepartmentsRepository;
import com.sample.employmanagementsystem.repository.EmployeeDetailsRepository;
import com.sample.employmanagementsystem.repository.RolesNameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    EmployeeDetailsRepository employeeDetailsRepository;

    @Autowired
    RolesNameRepository rolesNameRepository;

    @Autowired
    DepartmentsRepository departmentsRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public EmployeeDetails getemployeeDetails(Integer id) {
        try {
            log.info("Getting employee details: {}", id);
            return employeeDetailsRepository.findById(id).get();
        } catch (ResourceAccessException ex) {
            throw new EmployeeNotFoundException("Employee not found!");
        } catch (NoSuchElementException ex) {
            log.error("Employee not found!", ex);
            throw new InvalidDataInputException("Employee not found!");
        }
    }

    public List<EmployeeDetails> getAllemployeeDetails() {

        return employeeDetailsRepository.findAll();
    }

    @Override
    public EmployeeDetails addEmployeeDetails(EmployeeDetailsDto employeeDetailsDto) {

        try {
            EmployeeDetails employeeDetails = new EmployeeDetails();
            employeeDetails.setPhone(employeeDetailsDto.getPhone());
            employeeDetails.setName(employeeDetailsDto.getName());
            employeeDetails.setEmail(employeeDetailsDto.getEmail());
            employeeDetails.setUserName(employeeDetailsDto.getUserName());
            employeeDetails.setPassword(passwordEncoder.encode(employeeDetailsDto.getPassword()));
            employeeDetails.setSal(employeeDetailsDto.getSal());
            employeeDetails.setRole(rolesNameRepository.findById(employeeDetailsDto.getRoleId()).get());
            employeeDetails.setDepartments(departmentsRepository.findById(employeeDetailsDto.getDeptId()).get());

            employeeDetailsRepository.saveAndFlush(employeeDetails);
            return employeeDetails;
        } catch (Exception ex) {
            throw new RuntimeException("Employee details cannot be added");
        }
    }

    @Override
    public String deleteEmployeeDetails(Integer id) {

        employeeDetailsRepository.deleteById(id);
        return "Employee Deleted successfully";
    }

    @Override
    public String updateEmployeeDetails(Integer id, EmployeeDetails employeeDetails) {

        // employeeDetailsMap.put(id,employeeDetails);
        EmployeeDetails employeeDetails1 = employeeDetailsRepository.findById(id).get();
        employeeDetails1.setName(employeeDetails.getName());
        employeeDetails1.setPhone(employeeDetails.getPhone());
        employeeDetailsRepository.save(employeeDetails1);
        return "Employee Details updated successfully";
    }

    @Override
    public Roles getrolesName(Integer id) {

        return rolesNameRepository.findById(id).orElse(null);
    }

    @Override
    public String addRolesName(Roles roles) {
        rolesNameRepository.saveAndFlush(roles);
        return "Roles added successfully";
    }

    @Override
    public String addDepartments(Departments departments) {

        departmentsRepository.saveAndFlush(departments);
        return "Department name added successfully";
    }

    @Override
    public String getEmployeeNameById(Integer id) {
        return "Jack";
    }

    @Override
    public List<EmployeeDetails> getemployeeDetailsBy(Integer roleId) {

        try {
            return  employeeDetailsRepository.findByRoleId(roleId);
        } catch (Exception ex) {
            throw new RuntimeException("Employee details cannot be found");
        }
    }

//    @Override
//    public List<EmployeeDetails> getemployeeDetailsByUsername(String userName) {
//        List<EmployeeDetails> employeeDetailsList = employeeDetailsRepository.findByUserName(userName);
//        return employeeDetailsList;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        EmployeeDetails employee = employeeDetailsRepository.findByUserName(username);
         return User
                .withUsername(employee.getUsername())
                .password(employee.getPassword())
                .roles(employee.getRole().getName())
                .build();

    }
}
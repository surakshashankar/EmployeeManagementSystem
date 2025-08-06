package com.sample.employmanagementsystem.controller;

import com.sample.employmanagementsystem.Service.EmployeeService;
import com.sample.employmanagementsystem.entity.Departments;
import com.sample.employmanagementsystem.entity.EmployeeDetails;
import com.sample.employmanagementsystem.entity.Roles;
import com.sample.employmanagementsystem.models.EmployeeDetailsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Tag(name = "Employee Management" , description = "API's for managing employee details")
public class EmployeeDetailsController {

    @Autowired
    private EmployeeService employeeService;


    // Add employee
    @PostMapping("/add")
    public ResponseEntity<EmployeeDetails> addEmployee(@Valid @RequestBody EmployeeDetailsDto employeeDetails)
    {
        return new ResponseEntity<>(employeeService.addEmployeeDetails(employeeDetails), HttpStatus.CREATED);
    }

    @PostMapping("/add-roles")
    public String addRoles(@RequestBody Roles rolesName)
    {
        return employeeService.addRolesName(rolesName);
    }

    @PostMapping("/adddeptname")
    public String addRoles(@RequestBody Departments departments)
    {
        return employeeService.addDepartments(departments);
    }

    // Get all employees
    @GetMapping("/list")
    public List<EmployeeDetails> getAllEmployees()
    {
        System.out.println("im here");
        return employeeService.getAllemployeeDetails();
    }

    @GetMapping("/details/{id}")
    @Operation(summary = "Get Employee Details" , description = "Gets employee details with ID's")
    public ResponseEntity<EmployeeDetails> getEmployeeDetails(@PathVariable Integer id)
    {
        System.out.println("im here");
        return new ResponseEntity<>(employeeService.getemployeeDetails(id), HttpStatus.OK);

    }

//    @GetMapping("/details/{userName}")
//    public ResponseEntity<List<EmployeeDetails>> getAllEmployeeDetails(@PathVariable String userName)
//    {
//        return new ResponseEntity<>(employeeService.getemployeeDetailsByUsername(userName), HttpStatus.OK);
//    }

    @GetMapping("/list/{role_id}")
    public ResponseEntity<List<EmployeeDetails>> getAllEmployeeDetails(@PathVariable Integer role_id)
    {
        return new ResponseEntity<>(employeeService.getemployeeDetailsBy(role_id), HttpStatus.OK);
    }


    //Delete

    @DeleteMapping("/delete/{id}")
    public String removeEmployeeDetails(@PathVariable Integer id) {

        return employeeService.deleteEmployeeDetails(id);
    }

    @PutMapping("/edit/{id}")
    public String updateEmployeeDetails(@RequestBody EmployeeDetails employeeDetails, @PathVariable Integer id)
    {
        return employeeService.updateEmployeeDetails(id, employeeDetails);
    }

}






















/*package com.sample.employmanagementsystem.controller;

import com.sample.employmanagementsystem.entity.EmployeeDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployDetailsController
{
    @GetMapping("/employee/list")
    public List<String> getListOfEmployees()
    {
        List<String> employeeList= List.of("Ravi","Rekha","Sunil","Raju");
        return employeeList;
    }


    @GetMapping("/employeeDetails")
    public List<EmployeeDetails> getEmployeeDetails()
    {
        List<EmployeeDetails> employeeDetailsList = new ArrayList<>();
        EmployeeDetails employeeDetails1 = new EmployeeDetails();
        EmployeeDetails employeeDetails2 = new EmployeeDetails();
        EmployeeDetails employeeDetails3 = new EmployeeDetails();

        employeeDetails1.setEmpID(100);
        employeeDetails1.setDeptName("HR");
        employeeDetails1.setEmpEmail("Raju@gmal.com");
        employeeDetails1.setEmpName("Raju");
        employeeDetails1.setEmpPhone("9901405077");

       // List<String> employeeDetails2 = new ArrayList<String>();
        employeeDetails2.setEmpID(200);
        employeeDetails2.setDeptName("HR");
        employeeDetails2.setEmpEmail("Kavya@gmal.com");
        employeeDetails2.setEmpName("Kavya");
        employeeDetails2.setEmpPhone("99014346877");

        //List<String> employeeDetails3 = new ArrayList<String>();
        employeeDetails3.setEmpID(300);
        employeeDetails3.setDeptName("HR");
        employeeDetails3.setEmpEmail("Rhaul@gmal.com");
        employeeDetails3.setEmpName("Rhaul");
        employeeDetails3.setEmpPhone("9943050697");
employeeDetailsList.add(employeeDetails1);
employeeDetailsList.add(employeeDetails2);
employeeDetailsList.add(employeeDetails3);

        return employeeDetailsList;
    }

}*/


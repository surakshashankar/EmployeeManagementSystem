package com.sample.employmanagementsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.employmanagementsystem.Service.EmployeeService;
import com.sample.employmanagementsystem.ServiceImpl.CustomUserDetailsService;
import com.sample.employmanagementsystem.entity.Departments;
import com.sample.employmanagementsystem.entity.EmployeeDetails;
import com.sample.employmanagementsystem.entity.Roles;
import com.sample.employmanagementsystem.models.EmployeeDetailsDto;
import com.sample.employmanagementsystem.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeDetailsController.class)
@AutoConfigureMockMvc(addFilters = false)
class EmployeeDetailsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    EmployeeService employeeService;

    @MockitoBean
    private CustomUserDetailsService customUserDetailsService;

    @MockitoBean
    private JwtUtil jwtUtil;
    private Roles rolesName;

    @Test
    void getEmployeeDetails_SUCCESS() throws Exception {

        Integer id = 123;

        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setUserName("abcd");
        employeeDetails.setSal(15000);

        Mockito.when(employeeService.getemployeeDetails(id)).thenReturn(employeeDetails);

        mockMvc.perform(get("/employee/details/" + 123))
                .andExpect(status().isOk())
                .andExpect(jsonPath("username").value("abcd"))
                .andExpect(jsonPath("sal").value(15000));

    }

    @Test
    void postEmployeeDetails_SUCESS() throws Exception {

        EmployeeDetails employeeDetails = new EmployeeDetails();

        employeeDetails.setUserName("xyz");
        employeeDetails.setEmail("abc@gmail.com");

        EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto();
        employeeDetailsDto.setName(employeeDetails.getName());
        employeeDetailsDto.setEmail(employeeDetails.getEmail());
        employeeDetailsDto.setDeptId(1);

        Mockito.when(employeeService.addEmployeeDetails(employeeDetailsDto)).thenReturn(employeeDetails);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/employee/add")
                        .content(objectMapper.writeValueAsString(employeeDetailsDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("username").value("xyz"));

    }

    @Test
    void postEmployeeDetails1_SUCESS() throws Exception {

        EmployeeDetails employeeDetails = new EmployeeDetails();

        employeeDetails.setUserName("xyz");
        employeeDetails.setId(123);

        Roles roles = new Roles(123, "HR");
        roles.setRole("HR");

        Mockito.when(employeeService.addRolesName(roles)).thenReturn(String.valueOf(employeeDetails));

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/employee/add-roles")
                        .content(objectMapper.writeValueAsString(roles))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void postEmployeeDetails2_SUCESS() throws Exception {

        EmployeeDetails employeeDetails = new EmployeeDetails();

        employeeDetails.setUserName("xyz");
        employeeDetails.setId(123);

        Departments dept = new Departments(123, "Engineering");
        dept.setDeptName("Engineering");

        Mockito.when(employeeService.addDepartments(dept)).thenReturn(String.valueOf(employeeDetails));

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/employee/adddeptname")
                        .content(objectMapper.writeValueAsString(dept))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}


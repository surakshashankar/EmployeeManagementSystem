package com.sample.employmanagementsystem.service;

import com.sample.employmanagementsystem.ServiceImpl.EmployeeServiceImpl;
import com.sample.employmanagementsystem.entity.Departments;
import com.sample.employmanagementsystem.entity.EmployeeDetails;
import com.sample.employmanagementsystem.entity.Roles;
import com.sample.employmanagementsystem.exception.EmployeeNotFoundException;
import com.sample.employmanagementsystem.exception.InvalidDataInputException;
import com.sample.employmanagementsystem.models.EmployeeDetailsDto;
import com.sample.employmanagementsystem.repository.DepartmentsRepository;
import com.sample.employmanagementsystem.repository.EmployeeDetailsRepository;
import com.sample.employmanagementsystem.repository.RolesNameRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.ResourceAccessException;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;

@Tag("unit")
class EmployeeServiceImplTest {

  @InjectMocks
  private EmployeeServiceImpl employeeService;

  @Mock
  private EmployeeDetailsRepository employeeDetailsRepository;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Mock
  private PasswordEncoder passwordEncoder;

  @Mock
  private RolesNameRepository rolesNameRepository;

  @Mock
  private DepartmentsRepository departmentsRepository;

  @Test
  void testGetEmployeeDetails_SUCCESS() {
    EmployeeDetails employeeDetails = new EmployeeDetails();
    employeeDetails.setUserName("abc");

    Mockito.when(employeeDetailsRepository.findById(1)).thenReturn(Optional.of(employeeDetails));

    EmployeeDetails finalResult = employeeService.getemployeeDetails(1);

    Assertions.assertEquals(finalResult, employeeDetails);

  }
  @Test
  void testGetEmployeeDetails_throwsException() {

    Mockito.when(employeeDetailsRepository.findById(1)).thenThrow(ResourceAccessException.class);

    Assertions.assertThrows(EmployeeNotFoundException.class,
            ()-> employeeService.getemployeeDetails(1));

    Mockito.when(employeeDetailsRepository.findById(2)).thenThrow(NoSuchElementException.class);

    Assertions.assertThrows(InvalidDataInputException.class,
            ()-> employeeService.getemployeeDetails(2));
  }

  @Test
  void testAddEmployeeDetails_SUCCESS() {

    EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto();
    employeeDetailsDto.setName("sindhu");
    employeeDetailsDto.setPhone("2138273632");
    employeeDetailsDto.setEmail("sindhu@gmail.com");
    employeeDetailsDto.setSal(13172);
    employeeDetailsDto.setPassword("313124");
    employeeDetailsDto.setRoleId(2);
    employeeDetailsDto.setDeptId(3);
    Mockito.when(rolesNameRepository.findById(2)).thenReturn(Optional.of(new Roles(2, "HR")));
    Mockito.when(departmentsRepository.findById(3)).thenReturn(Optional.of(new Departments(3, "HR")));

    EmployeeDetails finalResult = employeeService.addEmployeeDetails(employeeDetailsDto);

    Assertions.assertEquals(finalResult.getEmail(), employeeDetailsDto.getEmail());
  }

  @Test
  void testAddEmployeeDetails_throwException() {

    EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto();
    employeeDetailsDto.setName("sindhu");
    employeeDetailsDto.setDeptId(3);

    Assertions.assertThrows(RuntimeException.class,
            ()-> employeeService.addEmployeeDetails(employeeDetailsDto));
  }

  @Test
  void testDeleteEmployeeDetails_SUCCESS() {

//    Mockito.when(employeeDetailsRepository.deleteById(123)).thenReturn(employeeDetails);
    doNothing().when(employeeDetailsRepository).deleteById(123);

    String finalResult = employeeService.deleteEmployeeDetails(123);

    Assertions.assertEquals("Employee Deleted successfully",finalResult);
  }

  @Test
  void testUpdateEmployeeDetails_SUCCESS(){

    EmployeeDetails employeeDetails = new EmployeeDetails();
    employeeDetails.setName("sindhu");

    Mockito.when(employeeDetailsRepository.findById(12)).thenReturn(Optional.of(employeeDetails));

    String finalResult = employeeService.updateEmployeeDetails(12,employeeDetails);

    Assertions.assertEquals("Employee Details updated successfully",finalResult);
  }

  @Test
  void testGetRolesName_SUCCESS(){

    Roles roles = new Roles(13, "HR");

    Mockito.when(rolesNameRepository.findById(13)).thenReturn(Optional.of(roles));

    Roles finalResult = employeeService.getrolesName(13);

    Assertions.assertEquals(finalResult, roles);
  }

  @Test
  void testAddRolesName_SUCCESS(){

    Roles roles = new Roles();
    roles.setRole("Engineering");

//    Mockito.when(rolesNameRepository.findById(14)).thenReturn(Optional.of(roles));

    String finalResult = employeeService.addRolesName(roles);

    Assertions.assertEquals("Roles added successfully", finalResult);
  }
}

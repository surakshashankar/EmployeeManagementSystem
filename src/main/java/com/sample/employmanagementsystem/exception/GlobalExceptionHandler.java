package com.sample.employmanagementsystem.exception;

import com.sample.employmanagementsystem.Service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Map<String, Object>> employeeNotFound(EmployeeNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", new Date());
        response.put("status", HttpStatus.NOT_FOUND);
        response.put("message", "Requested Employee is not present, Please provide valid Id");
        response.put("exception", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({InvalidDataInputException.class,MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Map<String, Object>> invalidDataInputException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", new Date());
        response.put("status", HttpStatus.NOT_FOUND);
        response.put("message", "EmployeeId should be Integer value");
        response.put("exception", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}

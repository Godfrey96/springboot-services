package com.skomane.employees.service;

import com.skomane.employees.dto.APIResponseDto;
import com.skomane.employees.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getAllEmployees();
    EmployeeDto addNewEmployee(EmployeeDto employeeDto);
    APIResponseDto getEmployeeById(Long employeeId);
}

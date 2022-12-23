package com.skomane.employees.service.impl;

import com.skomane.department.dto.DepartmentDto;
import com.skomane.employees.dto.APIResponseDto;
import com.skomane.employees.dto.EmployeeDto;
import com.skomane.employees.model.Employee;
import com.skomane.employees.repository.EmployeeRepository;
import com.skomane.employees.service.EmployeeService;
import com.skomane.organizations.dto.OrganizationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final WebClient.Builder webClientBuilder;

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(this::mapToEmployeeDto).collect(toList());
    }

    @Override
    public EmployeeDto addNewEmployee(EmployeeDto employeeDto) {

        // convert employee dto to employee model
        Employee employee = mapToEmployeeEntity(employeeDto);
        Employee addEmployee = employeeRepository.save(employee);

        // convert employee model to employee dto
        EmployeeDto saveEmployee = mapToEmployeeDto(addEmployee);

        return saveEmployee;
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {

        // get the employee by id
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found " + employeeId));

        // get the employee dto
        EmployeeDto employeeDto = mapToEmployeeDto(employee);

        // ge the department dto
        DepartmentDto departmentDto = webClientBuilder.build()
                .get()
                .uri("http://department-service/api/v1/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        // get the organization dto
        OrganizationDto organizationDto = webClientBuilder.build()
                .get()
                .uri("http://organization-service/api/v1/organizations/" + employee.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);
        apiResponseDto.setOrganizationDto(organizationDto);

        return apiResponseDto;
    }

    private EmployeeDto mapToEmployeeDto(Employee employee) {

        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

        return employeeDto;
    }

    private Employee mapToEmployeeEntity(EmployeeDto employeeDto) {

        Employee employee = modelMapper.map(employeeDto, Employee.class);

        return employee;
    }
}

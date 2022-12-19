package com.skomane.department.service;

import com.skomane.department.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    List<DepartmentDto> getAllDepartments();

    DepartmentDto addNewDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentByCode(String code);
}

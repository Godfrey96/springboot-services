package com.skomane.department.service;

import com.skomane.department.dto.DepartmentDto;

public interface DepartmentService {

    DepartmentDto addNewDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentByCode(String code);
}

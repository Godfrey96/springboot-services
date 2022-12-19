package com.skomane.department.service.impl;

import com.skomane.department.dto.DepartmentDto;
import com.skomane.department.model.Department;
import com.skomane.department.repository.DepartmentRepository;
import com.skomane.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(this::mapToDTO).collect(toList());
    }

    @Override
    public DepartmentDto addNewDepartment(DepartmentDto departmentDto) {
        
        // convert dto to entity
        Department department = mapToEntity(departmentDto);
        Department addDepartment = departmentRepository.save(department);

        // convert entity to dto
        DepartmentDto addDepartmentDto = mapToDTO(addDepartment);

        return addDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {

        Department department = departmentRepository.findByDepartmentCode(departmentCode);
//
        DepartmentDto departmentDto = mapToDTO(department);
//
        return departmentDto;
//        return null;
    }

    // Convert Entity to DTO
    private DepartmentDto mapToDTO(Department department) {
        DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);

//        DepartmentDto departmentDto = new DepartmentDto(
//                department.getId(),
//                department.getDepartmentName(),
//                department.getDepartmentDescription(),
//                department.getDepartmentCode()
//        );

        return departmentDto;
    }

    // Convert DTO to Entity
    private Department mapToEntity(DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto, Department.class);

//        Department department = new Department(
//                departmentDto.getId(),
//                departmentDto.getDepartmentName(),
//                departmentDto.getDepartmentDescription(),
//                departmentDto.getDepartmentCode()
//        );

        return department;
    }
}

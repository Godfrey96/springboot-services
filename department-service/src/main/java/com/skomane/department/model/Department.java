package com.skomane.department.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Department name cannot be null or empty")
    @Column(name = "department_name")
    private String departmentName;

    @NotBlank(message = "Department description cannot be null or empty")
    @Column(name = "department_description")
    private String departmentDescription;

    @NotBlank(message = "Department code cannot be null or empty")
    @Column(name = "department_code")
    private String departmentCode;
}

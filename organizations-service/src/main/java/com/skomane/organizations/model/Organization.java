package com.skomane.organizations.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Organization name cannot be null or empty")
    private String organizationName;

    @NotBlank(message = "Organization description cannot be null or empty")
    private String organizationDescription;

    @NotBlank(message = "Organization code cannot be null or empty")
    private String organizationCode;

    private LocalDateTime createdDate;
}

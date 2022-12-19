package com.skomane.organizations.controller;

import com.skomane.organizations.dto.OrganizationDto;
import com.skomane.organizations.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationDto> addNewOrganization(@RequestBody OrganizationDto organizationDto){
        organizationService.addOrganization(organizationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrganizationDto>> getAllOrganizations(){
        return new ResponseEntity<>(organizationService.getAllOrganizations(), HttpStatus.OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable String code){
        return new ResponseEntity<>(organizationService.getOrganizationByCode(code), HttpStatus.OK);
    }

}

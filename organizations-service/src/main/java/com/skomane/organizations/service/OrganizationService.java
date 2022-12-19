package com.skomane.organizations.service;

import com.skomane.organizations.dto.OrganizationDto;

import java.util.List;

public interface OrganizationService {

    List<OrganizationDto> getAllOrganizations();
    OrganizationDto addOrganization(OrganizationDto organizationDto);
    OrganizationDto getOrganizationByCode(String organizationCode);
}

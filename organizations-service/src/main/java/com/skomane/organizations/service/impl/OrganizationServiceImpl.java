package com.skomane.organizations.service.impl;

import com.skomane.organizations.dto.OrganizationDto;
import com.skomane.organizations.model.Organization;
import com.skomane.organizations.repository.OrganizationRepository;
import com.skomane.organizations.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<OrganizationDto> getAllOrganizations() {
        List<Organization> organizations = organizationRepository.findAll();
        return organizations.stream().map(this::mapToOrganizationDTo).collect(toList());
    }

    @Override
    public OrganizationDto addOrganization(OrganizationDto organizationDto) {

        // convert organization dto to organization model
        Organization organization = mapToOrganizationEntity(organizationDto);
        Organization saveOrganization = organizationRepository.save(organization);

        // convert organization model to organization dto
        OrganizationDto saveOrganizationDto = mapToOrganizationDTo(saveOrganization);

        return saveOrganizationDto;
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {

        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);

        OrganizationDto organizationDto = mapToOrganizationDTo(organization);

        return organizationDto;
    }

    // Convert organization dto to organization model
    private Organization mapToOrganizationEntity(OrganizationDto organizationDto) {
        Organization organization = modelMapper.map(organizationDto, Organization.class);

        return organization;
    }

    // Convert organization model to organization dto
    private OrganizationDto mapToOrganizationDTo(Organization organization) {
        OrganizationDto organizationDto = modelMapper.map(organization, OrganizationDto.class);

        return organizationDto;
    }
}

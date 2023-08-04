package com.tr.domain.services;

import com.tr.domain.entities.Address;
import com.tr.domain.entities.Company;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import com.tr.domain.repositories.CompanyRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyService {
  private static final String MSG_COMPANY_NOT_FOUND =
      "There is no company registration with the code %d";
  private static final String MSG_COMPANY_IN_USE =
      "Code company %d cannot be removed as it is in use";

  @Autowired 
  private CompanyRepository companyRepository;

  @Autowired 
  private AddressService addressService;

  public Company createCompany(Company company) {

    Long addressId = company.getAddress().getAddressId();
    Address address = addressService.findAddressById(addressId);

    company.setAddress(address);
    return companyRepository.save(company);
  }

  @Transactional
  public Company updateCompany(final Company company) {
    Company entity =
        companyRepository
            .findById(company.getCompanyId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setName(entity.getName());
    entity.setCnpj(entity.getCnpj());
    entity.setEmail(entity.getEmail());
    entity.setLogo(entity.getLogo());
    entity.setCelphone(entity.getCelphone());
    entity.setPhone(entity.getPhone());
    entity.setDateRegistration(entity.getDateRegistration());
    entity.setStateRegister(entity.getStateRegister());
    entity.setMunicipalRegister(entity.getMunicipalRegister());

    return companyRepository.save(company);
  }

  @Transactional
  public List<Company> findAllByCompany() {

    return companyRepository.findAll();
  }

  @Transactional
  public Company findCompanyById(final Long companyId) {
    return companyRepository
        .findById(companyId)
        .orElseThrow(
            () -> new EntityNotFoundException(String.format(MSG_COMPANY_NOT_FOUND, companyId)));
  }

  public void deleteCompany(Long companyId) {
    try {
      companyRepository.deleteById(companyId);

    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_COMPANY_NOT_FOUND, companyId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_COMPANY_IN_USE, companyId));
    }
  }
}

package com.tr.domain.Employees;

import com.tr.domain.Address.AddressEntity;
import com.tr.domain.Address.AddressService;
import com.tr.domain.Departament.DepartamentEntity;
import com.tr.domain.Departament.DepartamentService;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeesService {

  private static final String MSG_EMPLOYEES_NOT_FOUND =
      "There is no city registration with the code %d";
  private static final String MSG_EMPLOYEES_IN_USE =
      "Code city %d cannot be removed as it is in use";

  @Autowired private EmployeesRepository repository;
  @Autowired private AddressService addressService;
  @Autowired private DepartamentService departamentService;

  @Transactional
  public EmployeesEntity createEmployees(final EmployeesEntity employees) {
    Long addressId = employees.getAddress().getAddressId();
    AddressEntity address = addressService.findAddressById(addressId);

    Long departamentId = employees.getDepartament().getDepartamentId();
    DepartamentEntity departament = departamentService.findDepartamentById(departamentId);

    employees.setAddress(address);
    employees.setDepartament(departament);
    return repository.save(employees);
  }

  @Transactional
  public EmployeesEntity findEmployessById(final Long employeesId) {
    return repository
        .findById(employeesId)
        .orElseThrow(
            () -> new EntityNotFoundException(String.format(MSG_EMPLOYEES_NOT_FOUND, employeesId)));
  }

  @Transactional
  public EmployeesEntity updateEmployess(final EmployeesEntity employees) {
    EmployeesEntity entity =
        repository
            .findById(employees.getEmployeesId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setName(entity.getName());
    entity.setEmail(entity.getEmail());
    entity.setCpf(entity.getCpf());
    entity.setGenerealRegister(entity.getGenerealRegister());
    entity.setDepartament(entity.getDepartament());
    entity.setSalary(entity.getSalary());
    entity.setFunction(entity.getFunction());
    entity.setNumberLicenceDriver(entity.getNumberLicenceDriver());
    entity.setSpouse(entity.getSpouse());
    entity.setSchooling(entity.getSchooling());
    entity.setWorkHours(entity.getWorkHours());
    entity.setFoto(entity.getFoto());
    entity.setBirthday(entity.getBirthday());
    entity.setWorkCard(entity.getWorkCard());
    entity.setPispasep(entity.getPispasep());
    entity.setAdmission(entity.getAdmission());
    entity.setDemission(entity.getDemission());
    entity.setExames(entity.getExames());

    return repository.save(employees);
  }

  public void deleteEmployess(Long employeesId) {
    try {
      repository.deleteById(employeesId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_EMPLOYEES_NOT_FOUND, employeesId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_EMPLOYEES_IN_USE, employeesId));
    }
  }

  public List<EmployeesEntity> findAllByEmployees() {

    return repository.findAll();
  }
}

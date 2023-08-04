package com.tr.domain.services;

import com.tr.domain.entities.Address;
import com.tr.domain.entities.Departament;
import com.tr.domain.entities.Driver;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import com.tr.domain.repositories.DriverRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DriverService {
  private static final String MSG_DRIVER_NOT_FOUND =
      "There is no state registration with the code %d";
  private static final String MSG_DRIVER_IN_USE = "Code state %d cannot be removed as it is in use";

  @Autowired private DriverRepository driverRepository;
  @Autowired private AddressService addressService;

  @Autowired private DepartamentService departamentService;

  public Driver createDriver(Driver driver) {
    Long addressId = driver.getAddress().getAddressId();
    Address address = addressService.findAddressById(addressId);

    Long departamentId = driver.getDepartament().getDepartamentId();
    Departament departament = departamentService.findDepartamentById(departamentId);

    driver.setAddress(address);
    driver.setDepartament(departament);
    return driverRepository.save(driver);
  }

  @Transactional
  public Driver updateDriver(final Driver driver) {
    Driver entity =
        driverRepository
            .findById(driver.getDriverId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setName(entity.getName());
    entity.setEmail(entity.getEmail());
    entity.setCelphone(entity.getCelphone());
    entity.setPhone(entity.getPhone());
    entity.setDateBirthday(entity.getDateBirthday());
    entity.setCpf(entity.getCpf());
    entity.setGeneralRegister(entity.getGeneralRegister());

    return driverRepository.save(driver);
  }

  @Transactional
  public List<Driver> findAllByDrivers() {
    
    return driverRepository.findAll();
  }

  @Transactional
  public Driver findDriverById(final Long driverId) {
    return driverRepository
        .findById(driverId)
        .orElseThrow(
            () -> new EntityNotFoundException(String.format(MSG_DRIVER_NOT_FOUND, driverId)));
  }

  public void deleteDriver(Long driverId) {
    try {
      driverRepository.deleteById(driverId);

    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_DRIVER_NOT_FOUND, driverId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_DRIVER_IN_USE, driverId));
    }
  }
}

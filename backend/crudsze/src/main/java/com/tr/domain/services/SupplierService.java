package com.tr.domain.services;

import com.tr.domain.entities.Address;
import com.tr.domain.entities.Supplier;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import com.tr.domain.repositories.SupplierRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SupplierService {
  
  private static final String MSG_SUPPLIER_NOT_FOUND = "There is no supplier registration with the code %d";
  private static final String MSG_SUPPLIER_IN_USE = "Code supplier %d cannot be removed as it is in use";

  @Autowired
  private SupplierRepository repository;
  @Autowired private AddressService addressService;

  @Transactional
  public Supplier createSupplier(final Supplier supplier) {
    Long addressId = supplier.getAddress().getAddressId();
    Address address = addressService.findAddressById(addressId);

    supplier.setAddress(address);
    return repository.save(supplier);
  }

  @Transactional
  public Supplier findSupplierById(final Long supplierId) {
    return repository
        .findById(supplierId)
        .orElseThrow(() -> new EntityNotFoundException(String.format(MSG_SUPPLIER_NOT_FOUND, supplierId)));
  }

  @Transactional
  public Supplier updateSupplier(final Supplier supplier) {
    var entity =
        repository
            .findById(supplier.getSupplierId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setName(entity.getName());
    entity.setEmail(entity.getEmail());
    entity.setCelphone(entity.getCelphone());
    entity.setCnpj(entity.getCnpj());
    entity.setCorpareteName(entity.getCorpareteName());
    entity.setDateOfLastPurchase(entity.getDateOfLastPurchase());
    entity.setDateRegistration(entity.getDateRegistration());
    entity.setFantasyName(entity.getFantasyName());
    entity.setMunicipalRegistration(entity.getMunicipalRegistration());
    
    return repository.save(supplier);
  }
  
  public void deleteSupplier(Long supplierId) {
    try {
      repository.deleteById(supplierId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_SUPPLIER_NOT_FOUND, supplierId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_SUPPLIER_IN_USE, supplierId));
    }
  }

  public List<Supplier> findAllBySupplier() {

    return repository.findAll();
  }
}

package com.tr.domain.Address;

import com.tr.domain.City.CityEntity;
import com.tr.domain.City.CityService;
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
public class AddressService {

  private static final String MSG_ADDRESS_NOT_FOUND =
      "There is no address registration with the code %d";
  private static final String MSG_ADDRESS_IN_USE =
      "Code address %d cannot be removed as it is in use";

  @Autowired private final AddressRespository respository;

  @Autowired private final CityService cityService;

  @Transactional
  public AddressEntity createAddress(AddressEntity address) {
    Long cityId = address.getCity().getCityId();
    CityEntity city = cityService.findCityById(cityId);

    address.setCity(city);
    return respository.save(address);
  }

  @Transactional
  public List<AddressEntity> findAllByAddress() {
    return respository.findAll();
  }

  @Transactional
  public AddressEntity findAddressById(final Long addressId) {
    return respository
        .findById(addressId)
        .orElseThrow(
            () -> new EntityNotFoundException(String.format(MSG_ADDRESS_NOT_FOUND, addressId)));
  }

  @Transactional
  public AddressEntity updateAddress(final AddressEntity address) {
    AddressEntity entity =
        respository
            .findById(address.getAddressId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setPublicPlace(entity.getPublicPlace());
    entity.setPostalCode(entity.getPostalCode());
    entity.setComplement(entity.getComplement());
    entity.setNewCodePostal(entity.getNewCodePostal());
    entity.setNeighborhood(entity.getNeighborhood());
    entity.setNumber(entity.getNumber());

    return respository.save(address);
  }

    public void deleteAddress(Long addressId) {
      try {
        respository.deleteById(addressId);
      } catch (EmptyResultDataAccessException e) {
        throw new EntityNotFoundException(String.format(MSG_ADDRESS_NOT_FOUND, addressId));
  
      } catch (DataIntegrityViolationException e) {
        throw new EntityInUseException(String.format(MSG_ADDRESS_IN_USE, addressId));
      }
    }
}

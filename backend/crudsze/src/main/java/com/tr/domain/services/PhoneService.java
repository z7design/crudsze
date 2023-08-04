package com.tr.domain.services;

import com.tr.domain.entities.Phone;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import com.tr.domain.repositories.PhoneRespository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PhoneService {
  private static final String MSG_PHONE_NOT_FOUND =
      "There is no state registration with the code %d";
  private static final String MSG_PHONE_IN_USE = "Code state %d cannot be removed as it is in use";

  @Autowired private PhoneRespository phoneRepository;

  public Phone createPhone(Phone phone) {
    return phoneRepository.save(phone);
  }

  @Transactional
  public Phone updatePhone(final Phone phone) {
    Phone entity =
        phoneRepository
            .findById(phone.getPhoneId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setDdd(entity.getDdd());
    entity.setDdi(entity.getDdi());
    entity.setNumber(entity.getNumber());

    return phoneRepository.save(phone);
  }

  @Transactional
  public List<Phone> findAllByPhone() {
    return phoneRepository.findAll();
  }

  @Transactional
  public Phone findPhoneById(final Long phoneId) {
    return phoneRepository
        .findById(phoneId)
        .orElseThrow(
            () -> new EntityNotFoundException(String.format(MSG_PHONE_NOT_FOUND, phoneId)));
  }

  public void deletePhone(Long phoneId) {
    try {
      phoneRepository.deleteById(phoneId);

    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_PHONE_NOT_FOUND, phoneId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_PHONE_IN_USE, phoneId));
    }
  }
}

package com.tr.domain.Phone;

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
public class PhoneService {
  private static final String MSG_PHONE_NOT_FOUND =
      "There is no state registration with the code %d";
  private static final String MSG_PHONE_IN_USE = "Code state %d cannot be removed as it is in use";

  @Autowired private PhoneRespository phoneRepository;

  public PhoneEntity createPhone(PhoneEntity phone) {
    return phoneRepository.save(phone);
  }

  @Transactional
  public PhoneEntity updatePhone(final PhoneEntity phone) {
    PhoneEntity entity =
        phoneRepository
            .findById(phone.getPhoneId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setDdd(entity.getDdd());
    entity.setDdi(entity.getDdi());
    entity.setNumber(entity.getNumber());

    return phoneRepository.save(phone);
  }

  @Transactional
  public List<PhoneEntity> findAllByPhone() {
    return phoneRepository.findAll();
  }

  @Transactional
  public PhoneEntity findPhoneById(final Long phoneId) {
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

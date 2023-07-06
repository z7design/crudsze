package com.tr.domain.services;

import com.tr.domain.entities.City;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.repositories.CityRepository;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
@AllArgsConstructor
public class CityService {

  private static final String MSG_CATEGORY_NOT_FOUND =
      "There is no category registration with the code %d";
  private static final String MSG_CATEGORY_IN_USE =
      "Code category %d cannot be removed as it is in use";

  public EntityManager manager;
  
  @Autowired
  private CityRepository repository;
  
  @Transactional(readOnly = true)
  public City createCity(City city){
    return repository.save(city);
  }

  @Transactional(readOnly = true)
  public List<City> findByCity(){
    return repository.findAll();
  }

  @DeleteMapping("/{cityId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCity(UUID cityId) {
    try {
      repository.deleteById(cityId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_CATEGORY_NOT_FOUND, cityId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_CATEGORY_IN_USE, cityId));
    }
  }

  public City buscarOuFalhar(UUID cityId) {
    return repository
        .findById(cityId)
        .orElseThrow(
            () -> new EntityNotFoundException(String.format(MSG_CATEGORY_NOT_FOUND, cityId)));
  }
}

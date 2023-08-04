package com.tr.domain.services;

import com.tr.domain.entities.City;
import com.tr.domain.entities.State;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import com.tr.domain.repositories.CityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityService {

  private static final String MSG_CITY_NOT_FOUND = "There is no city registration with the code %d";
  private static final String MSG_CITY_IN_USE = "Code city %d cannot be removed as it is in use";

  @Autowired private CityRepository repositoryCity;
  @Autowired private StateService stateService;

  @Transactional
  public City createCity(final City city) {
    Long stateId = city.getState().getStateId();
    State state = stateService.findStateById(stateId);

    city.setState(state);
    return repositoryCity.save(city);
  }

  @Transactional
  public City findCityById(final Long cityId) {
    return repositoryCity
        .findById(cityId)
        .orElseThrow(() -> new EntityNotFoundException(String.format(MSG_CITY_NOT_FOUND, cityId)));
  }

  @Transactional
  public City updateCity(final City city) {
    City entity =
        repositoryCity
            .findById(city.getCityId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setName(entity.getName());
    return repositoryCity.save(city);
  }
  
  public void deleteCity(Long cityId) {
    try {
      repositoryCity.deleteById(cityId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_CITY_NOT_FOUND, cityId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_CITY_IN_USE, cityId));
    }
  }

  public List<City> findAllByCities() {
    return repositoryCity.findAll();
  }
}

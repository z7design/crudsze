package com.tr.api.controllers;

import com.tr.domain.entities.City;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.services.CityService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/cities")
public class CityController {

  @Autowired private CityService service;

  @GetMapping
  public List<City> findAllByCity() {

    return service.findAllByCities();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{cityId}")
  public City getByCityId(@PathVariable Long cityId) {
    return service.findCityById(cityId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{cityId}")
  public City update(@PathVariable Long cityId, @RequestBody City city) {
    City cityCurrent = service.findCityById(cityId);
    BeanUtils.copyProperties(city, cityCurrent, "cityId");
    return service.createCity(cityCurrent);
  }

  @PostMapping
  public ResponseEntity<City> createCity(@RequestBody City city) {
    try {
      city = service.createCity(city);
      return ResponseEntity.status(HttpStatus.CREATED).body(city);

    } catch (EntityNotFoundException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{cityId}")
  public void deleteCity(@PathVariable Long cityId) {

    service.deleteCity(cityId);
  }
}

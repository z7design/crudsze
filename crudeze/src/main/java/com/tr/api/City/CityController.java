package com.tr.api.City;

import com.tr.domain.City.CityEntity;
import com.tr.domain.City.CityService;
import com.tr.domain.exception.EntityNotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/api/cities")
public class CityController {

  @Autowired private CityService service;

  @GetMapping
  public List<CityEntity> findAllByCity() {
    return service.findAllByCities();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{cityId}")
  public CityEntity getByCityId(@PathVariable Long cityId) {
    return service.findCityById(cityId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{cityId}")
  public CityEntity update(@PathVariable Long cityId, @RequestBody CityEntity city) {
    CityEntity cityCurrent = service.findCityById(cityId);
    BeanUtils.copyProperties(city, cityCurrent, "cityId");
    return service.createCity(cityCurrent);
  }

  @ResponseStatus(HttpStatus.OK)
  @PostMapping
  public ResponseEntity<CityEntity> createCity(@RequestBody CityEntity city) {
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

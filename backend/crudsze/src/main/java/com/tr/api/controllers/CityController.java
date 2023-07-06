package com.tr.api.controllers;

import com.tr.domain.entities.City;
import com.tr.domain.services.CityService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/cities")
public class CityController {
  
  @Autowired
  private CityService service;
  
  @GetMapping
  public List<City> findAll(){
    return service.findByCity();
  }
  
  @PostMapping
  public City createCity(@PathVariable City city){
    return service.createCity(city);
  }
}

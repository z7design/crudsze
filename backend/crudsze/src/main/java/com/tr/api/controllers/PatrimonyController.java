package com.tr.api.controllers;

import com.tr.domain.entities.Patrimony;
import com.tr.domain.services.PatrimonyService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/patrimony")
public class PatrimonyController {

  @Autowired private PatrimonyService service;

  @GetMapping
  public List<Patrimony> findAllByPatrimony() {
    return service.findAllByPatrimony();
  }

  @GetMapping("/{patrimonyId}")
  public Patrimony getByPatrimonyId(@PathVariable Long patrimonyId) {
    return service.findPatrimonyById(patrimonyId);
  }

  @PutMapping("/{patrimonyId}")
  public Patrimony update(@PathVariable Long patrimonyId, @RequestBody Patrimony patrimony) {
    Patrimony patrimonyCurrent = service.findPatrimonyById(patrimonyId);
    BeanUtils.copyProperties(patrimony, patrimonyCurrent, "patrimonyId");
    return service.createPatrimony(patrimonyCurrent);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Patrimony createPatrimony(@RequestBody Patrimony patrimony) {
    return service.createPatrimony(patrimony);
  }

  @DeleteMapping("/{patrimonyId}")
  public void deleteByPatrimony(@PathVariable Long patrimonyId) {
    service.deletePatrimony(patrimonyId);
  }
}

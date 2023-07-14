package com.tr.api.controllers;

import com.tr.domain.entities.Checkbook;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.services.CheckbookService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/checkbooks")
public class CheckbookController {

  @Autowired private CheckbookService service;

  @GetMapping
  public List<Checkbook> findAllByCheckbook() {
    return service.findAllByCheckbook();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{checkbookId}")
  public Checkbook getByCheckbookId(@PathVariable Long checkbookId) {
    return service.findCheckbookById(checkbookId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{checkbookId}")
  public Checkbook update(@PathVariable Long checkbookId, @RequestBody Checkbook checkbook) {
    Checkbook checkbookCurrent = service.findCheckbookById(checkbookId);
    BeanUtils.copyProperties(checkbook, checkbookCurrent, "checkbookId");
    return service.createCheckbook(checkbookCurrent);
  }
  
  @PostMapping
  public ResponseEntity<Checkbook> createCheckbook(@RequestBody Checkbook checkbook) {
    try {
      checkbook = service.createCheckbook(checkbook);
      return ResponseEntity.status(HttpStatus.CREATED).body(checkbook);

    } catch (EntityNotFoundException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{checkbookId}")
  public void deleteCheckbook(@PathVariable Long checkbookId) {
    service.deleteCheckbook(checkbookId);
  }
}

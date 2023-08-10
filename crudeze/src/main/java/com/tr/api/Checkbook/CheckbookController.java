package com.tr.api.Checkbook;

import com.tr.domain.Checkbook.CheckbookEntity;
import com.tr.domain.Checkbook.CheckbookService;
import com.tr.domain.exception.EntityNotFoundException;
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
  public List<CheckbookEntity> findAllByCheckbook() {
    return service.findAllByCheckbook();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{checkbookId}")
  public CheckbookEntity getByCheckbookId(@PathVariable Long checkbookId) {
    return service.findCheckbookById(checkbookId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{checkbookId}")
  public CheckbookEntity update(@PathVariable Long checkbookId, @RequestBody CheckbookEntity checkbook) {
    CheckbookEntity checkbookCurrent = service.findCheckbookById(checkbookId);
    BeanUtils.copyProperties(checkbook, checkbookCurrent, "checkbookId");
    return service.createCheckbook(checkbookCurrent);
  }
  
  @PostMapping
  public ResponseEntity<CheckbookEntity> createCheckbook(@RequestBody CheckbookEntity checkbook) {
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

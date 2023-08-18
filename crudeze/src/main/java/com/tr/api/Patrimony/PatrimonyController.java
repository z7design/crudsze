package com.tr.api.Patrimony;

import com.tr.domain.Patrimony.PatrimonyEntity;
import com.tr.domain.Patrimony.PatrimonyService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/api/patrimony")
public class PatrimonyController {

  @Autowired private PatrimonyService service;

  @GetMapping
  public List<PatrimonyEntity> findAllByPatrimony() {

    return service.findAllByPatrimony();
  }

  @GetMapping("/{patrimonyId}")
  public PatrimonyEntity getByPatrimonyId(@PathVariable Long patrimonyId) {
    return service.findPatrimonyById(patrimonyId);
  }

  @PutMapping("/{patrimonyId}")
  public PatrimonyEntity update(@PathVariable Long patrimonyId, @RequestBody PatrimonyEntity patrimony) {
    PatrimonyEntity patrimonyCurrent = service.findPatrimonyById(patrimonyId);
    BeanUtils.copyProperties(patrimony, patrimonyCurrent, "patrimonyId");
    return service.createPatrimony(patrimonyCurrent);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PatrimonyEntity createPatrimony(@RequestBody PatrimonyEntity patrimony) {
    return service.createPatrimony(patrimony);
  }

  @DeleteMapping("/{patrimonyId}")
  public void deleteByPatrimony(@PathVariable Long patrimonyId) {
    service.deletePatrimony(patrimonyId);
  }
}

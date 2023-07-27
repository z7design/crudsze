package com.tr.api.controllers;

import com.tr.domain.entities.State;
import com.tr.domain.services.StateService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/states")
public class StateController {

  @Autowired private StateService service;

  @GetMapping
  public List<State> findAll() {
    return service.findAllByStates();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public State createState(@RequestBody State state) {
    return service.createState(state);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{stateId}")
  public State updateState(@PathVariable Long stateId, @RequestBody State state) {
    State stateCurrent = service.findStateById(stateId);
    BeanUtils.copyProperties(state, stateCurrent, "stateId");
    return service.createState(stateCurrent);
  }

  @DeleteMapping("/{stateId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteState(@PathVariable Long stateId) {

    service.deleteState(stateId);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{stateId}")
  public State findByIdStates(@PathVariable Long stateId) {

    return service.findStateById(stateId);
  }
}

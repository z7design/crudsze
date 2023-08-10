package com.tr.api.State;

import com.tr.domain.State.StateEntity;
import com.tr.domain.State.StateService;
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
  public List<StateEntity> findAll() {
    return service.findAllByStates();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public StateEntity createState(@RequestBody StateEntity state) {
    return service.createState(state);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{stateId}")
  public StateEntity updateState(@PathVariable Long stateId, @RequestBody StateEntity state) {
    StateEntity stateCurrent = service.findStateById(stateId);
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
  public StateEntity findByIdStates(@PathVariable Long stateId) {

    return service.findStateById(stateId);
  }
}

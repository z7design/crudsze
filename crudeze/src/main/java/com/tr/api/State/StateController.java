package com.tr.api.State;

import com.tr.domain.State.StateRequestDTO;
import com.tr.domain.State.StateResponse;
import com.tr.domain.State.StateService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/states")
public class StateController {

  @Autowired private StateService service;

  @GetMapping
  public ResponseEntity<List<StateResponse>> findAllStates() {
    return ResponseEntity.ok(service.findAllByStates());
  }

  @PostMapping
  public ResponseEntity<StateResponse> createState(@RequestBody StateRequestDTO dto) {
    StateResponse stateResponse = service.save(dto);
    return new ResponseEntity<>(stateResponse, HttpStatus.CREATED);
  }

  @PutMapping("/{stateId}")
  public ResponseEntity<StateResponse> updateState(@PathVariable Long stateId, @RequestBody StateRequestDTO dto) {
    return ResponseEntity.ok(service.update(stateId, dto));
  }

  @DeleteMapping("/{stateId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<?>deleteState(@PathVariable Long stateId) {
    service.deleteState(stateId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{stateId}")
  public ResponseEntity<StateResponse> findByIdStates(@PathVariable Long stateId) {
    return ResponseEntity.ok(service.findStateById(stateId));
  }
}

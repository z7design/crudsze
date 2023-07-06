package com.tr.api.controllers;

import com.tr.domain.entities.State;
import com.tr.domain.repositories.StateRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/states")
public class StateController {
  @Autowired
  private StateRepository repository;

  @GetMapping
  public List<State> findAll() {
    return repository.findAll();
  }

  @PostMapping
  public State createState(State state){
    return repository.save(state);
  }
}

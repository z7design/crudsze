package com.tr.api.controllers;

import com.tr.domain.entities.Client;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.services.ClientService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/clients")
public class ClientController {
  @Autowired private ClientService service;

  @GetMapping
  public List<Client> findAllByClient() {
    return service.findAllByClients();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{clientId}")
  public Client getByClientId(@PathVariable Long clientId) {
    return service.findClientById(clientId);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{clientId}")
  public Client update(@PathVariable Long clientId, @RequestBody Client client) {
    Client clientCurrent = service.findClientById(clientId);
    BeanUtils.copyProperties(client, clientCurrent, "clientId");
    return service.createClient(clientCurrent);
  }

  @PostMapping
  public ResponseEntity<Client> createClient(@RequestBody Client client) {
    try {
      client = service.createClient(client);
      return ResponseEntity.status(HttpStatus.CREATED).body(client);

    } catch (EntityNotFoundException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{clientId}")
  public void deleteClient(@PathVariable Long cityId) {

    service.deleteClient(cityId);
  }
}

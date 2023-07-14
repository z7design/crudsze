package com.tr.domain.services;

import com.tr.domain.entities.Address;
import com.tr.domain.entities.Client;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import com.tr.domain.repositories.ClientRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
@RequiredArgsConstructor
public class ClientService {

  private static final String MSG_CLIENT_NOT_FOUND =
      "There is no client registration with the code %d";
  private static final String MSG_CLIENT_IN_USE =
      "Code client %d cannot be removed as it is in use";

  @Autowired private ClientRepository repository;
  @Autowired private AddressService addressService;

  @Transactional
  public Client createClient(final Client client) {
    Long addressId = client.getAddress().getAddressId();
    Address address = addressService.findAddressById(addressId);

    client.setAddress(address);
    return repository.save(client);
  }

  @Transactional
  public Client findClientById(final Long clientId) {
    return repository
        .findById(clientId)
        .orElseThrow(
            () -> new EntityNotFoundException(String.format(MSG_CLIENT_NOT_FOUND, clientId)));
  }

  @Transactional
  public Client updateClient(final Client client) {
    var entity =
        repository
            .findById(client.getClientId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setName(entity.getName());
    return repository.save(client);
  }

  @DeleteMapping("/{clientId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteClient(Long clientId) {
    try {
      repository.deleteById(clientId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_CLIENT_NOT_FOUND, clientId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_CLIENT_IN_USE, clientId));
    }
  }

  public List<Client> findAllByClients() {

    return repository.findAll();
  }
}

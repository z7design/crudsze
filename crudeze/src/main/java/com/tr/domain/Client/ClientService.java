package com.tr.domain.Client;

import com.tr.domain.Address.AddressEntity;
import com.tr.domain.Address.AddressService;
import com.tr.domain.exception.EntityInUseException;
import com.tr.domain.exception.EntityNotFoundException;
import com.tr.domain.exception.ResourceNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  public ClientEntity createClient(final ClientEntity client) {
    Long addressId = client.getAddress().getAddressId();
    AddressEntity address = addressService.findAddressById(addressId);

    client.setAddress(address);
    return repository.save(client);
  }

  @Transactional
  public ClientEntity findClientById(final Long clientId) {
    return repository
        .findById(clientId)
        .orElseThrow(
            () -> new EntityNotFoundException(String.format(MSG_CLIENT_NOT_FOUND, clientId)));
  }

  @Transactional
  public ClientEntity updateClient(final ClientEntity client) {
    ClientEntity entity =
        repository
            .findById(client.getClientId())
            .orElseThrow(() -> new ResourceNotFoundException("Not fond"));

    entity.setName(entity.getName());
    return repository.save(client);
  }
  
  public void deleteClient(Long clientId) {
    try {
      repository.deleteById(clientId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(String.format(MSG_CLIENT_NOT_FOUND, clientId));

    } catch (DataIntegrityViolationException e) {
      throw new EntityInUseException(String.format(MSG_CLIENT_IN_USE, clientId));
    }
  }

  public List<ClientEntity> findAllByClients() {
    return repository.findAll();
  }
}

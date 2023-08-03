package com.tr.domain.services;

import com.tr.api.responses.TitlesResponse;
import com.tr.domain.dto.TitlesRequestDTO;
import com.tr.domain.entities.Titles;
import com.tr.domain.entities.User;
import com.tr.domain.exception.ResourceBadRequestException;
import com.tr.domain.exception.ResourceNotFoundException;
import com.tr.domain.repositories.TitlesRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TitlesService implements ICrudsServices<TitlesRequestDTO, TitlesResponse> {
  @Autowired private TitlesRepository repository;

  @Autowired private ModelMapper mapper;

  @Override
  public List<TitlesResponse> findAll() {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    List<Titles> titles = repository.findByUser(user);

    return titles.stream()
        .map(titulo -> mapper.map(titulo, TitlesResponse.class))
        .collect(Collectors.toList());
  }

  @Override
  public TitlesResponse findById(Long titlesId) {
    Optional<Titles> optionalTitles = repository.findById(titlesId);
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    if (optionalTitles.isEmpty()
        || optionalTitles.get().getUser().getUserId() != user.getUserId()) {
      throw new ResourceNotFoundException("Could not find title with id " + titlesId);
    }
    return mapper.map(optionalTitles.get(), TitlesResponse.class);
  }

  @Override
  public TitlesResponse save(TitlesRequestDTO dto) {
    validateTitle(dto);

    Titles title = mapper.map(dto, Titles.class);

    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    title.setUser(user);
    title.setTitlesId(null);
    title.setDateRegistration(new Date());
    title = repository.save(title);

    return mapper.map(title, TitlesResponse.class);
  }

  @Override
  public TitlesResponse update(Long titlesId, TitlesRequestDTO dto) {
    findById(titlesId);
    validateTitle(dto);

    Titles title = mapper.map(dto, Titles.class);

    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    title.setUser(user);
    title.setTitlesId(null);
    title.setDateRegistration(new Date());
    title = repository.save(title);

    return mapper.map(title, TitlesResponse.class);
  }

  @Override
  public void delete(Long titlesId) {
    findById(titlesId);
    repository.deleteById(titlesId);
  }
  
  public List<TitlesResponse> obterPorDataDeVenciment(String firstPeriod, String finishPeriod){
    List<Titles> titles = repository.obterFluxoCaixaPorDataVencimento(firstPeriod, finishPeriod);
    
    return titles.stream()
        .map(title -> mapper.map(title, TitlesResponse.class)).collect(Collectors.toList());
  }

  private void validateTitle(TitlesRequestDTO dto) {
    if (dto.getTypeTitle() == null
        || dto.getDueDate() == null
        || dto.getValueTitle() == null
        || dto.getDescription() == null) {
      throw new ResourceBadRequestException(
          "The Type, Expiration Date, Value and Description fields are mandatory.");
    }
  }
}

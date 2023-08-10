package com.tr.domain.Titles;

import com.tr.domain.ICrudsServices;
import com.tr.domain.User.UserEntity;
import com.tr.domain.exception.ResourceBadRequestException;
import com.tr.domain.exception.ResourceNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TitlesService implements ICrudsServices<TitlesRequestDTO, com.tr.domain.Titles.TitlesResponse> {
  @Autowired private TitlesRepository repository;

  @Autowired private ModelMapper mapper;

  @Override
  public List<com.tr.domain.Titles.TitlesResponse> findAll() {
    List<TitlesEntity> titles = repository.findAll();

    return titles.stream()
        .map(titulo -> mapper.map(titulo, com.tr.domain.Titles.TitlesResponse.class))
        .collect(Collectors.toList());
  }

  @Override
  public com.tr.domain.Titles.TitlesResponse findById(Long titlesId) {
    Optional<TitlesEntity> optionalTitles = repository.findById(titlesId);
    if (optionalTitles.isEmpty()) {
      throw new ResourceNotFoundException("Could not find title with id " + titlesId);
    }
    return mapper.map(optionalTitles.get(), com.tr.domain.Titles.TitlesResponse.class);
  }

  @Override
  public com.tr.domain.Titles.TitlesResponse save(TitlesRequestDTO dto) {
    validateTitle(dto);

    TitlesEntity title = mapper.map(dto, TitlesEntity.class);

    UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    title.setUser(user);
    title.setTitlesId(null);
    title.setDateRegistration(new Date());
    title = repository.save(title);

    return mapper.map(title, com.tr.domain.Titles.TitlesResponse.class);
  }

  @Override
  public com.tr.domain.Titles.TitlesResponse update(Long titlesId, TitlesRequestDTO dto) {
    findById(titlesId);
    validateTitle(dto);

    TitlesEntity title = mapper.map(dto, TitlesEntity.class);

    UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    title.setUser(user);
    title.setTitlesId(titlesId);
    title.setDateRegistration(new Date());
    title = repository.save(title);

    return mapper.map(title, com.tr.domain.Titles.TitlesResponse.class);
  }

  @Override
  public void delete(Long titlesId) {
    findById(titlesId);
    repository.deleteById(titlesId);
  }

  // obterPorDataDeVenciment
  public List<com.tr.domain.Titles.TitlesResponse> getByDueDate(String firstPeriod, String finishPeriod) {
    List<TitlesEntity> titles = repository.getCashFlowByDueDate(firstPeriod, finishPeriod);

    return titles.stream()
        .map(title -> mapper.map(title, TitlesResponse.class))
        .collect(Collectors.toList());
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

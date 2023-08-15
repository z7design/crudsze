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
public class TitlesService implements ICrudsServices<TitlesRequestDTO, TitlesResponse> {
  @Autowired private TitlesRepository repository;

  @Autowired private ModelMapper mapper;

  @Override
  public List<TitlesResponse> findAll() {
    UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    List<TitlesEntity> titles = repository.findByUserEntity(user);

    return titles.stream()
        .map(title -> mapper.map(title, TitlesResponse.class))
        .collect(Collectors.toList());
  }

  @Override
  public TitlesResponse findById(Long titlesId) {
    Optional<TitlesEntity> optionalTitles = repository.findById(titlesId);
    UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    if (optionalTitles == null
        || optionalTitles.get().getUserEntity().getUserId() != user.getUserId()) {
      throw new ResourceNotFoundException("Could not find title with id " + titlesId);
    }
    return mapper.map(optionalTitles.get(), TitlesResponse.class);
  }

  @Override
  public TitlesResponse save(TitlesRequestDTO dto) {

    validateTitle(dto);
    TitlesEntity title = mapper.map(dto, TitlesEntity.class);

    UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    title.setUserEntity(user);
    title.setTitlesId(null);
    title.setDateRegistration(new Date());
    title = repository.save(title);

    return mapper.map(title, TitlesResponse.class);
  }

  @Override
  public TitlesResponse update(Long titlesId, TitlesRequestDTO dto) {
    findById(titlesId);
    validateTitle(dto);

    TitlesEntity title = mapper.map(dto, TitlesEntity.class);

    UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    title.setUserEntity(user);
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

  // obterPorDataDeVenciment
  public List<TitlesResponse> getCashFlowByDueDate(String firstPeriod, String finishPeriod,
      UserEntity user) {
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

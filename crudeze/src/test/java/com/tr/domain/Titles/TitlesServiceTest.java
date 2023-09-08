package com.tr.domain.Titles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import com.tr.domain.CostCenter.CostCenterEntity;
import com.tr.domain.Enums.TypeTitle;
import com.tr.domain.User.UserEntity;
import com.tr.domain.Vehicle.VehicleEntity;
import com.tr.domain.exception.DatabaseException;
import com.tr.domain.exception.ResourceNotFoundException;
import com.tr.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

@ExtendWith(MockitoExtension.class)
class TitlesServiceTest {

  @InjectMocks private TitlesService service;
  @Mock private TitlesRepository repository;

  @Autowired private UserEntity user;

  @Autowired
  private JwtUtil jwtUtil;
  private List<CostCenterEntity> costCenter;
  private BigDecimal valueTitle = BigDecimal.valueOf(3056.20);
  @Autowired private TitlesEntity title;
  @Autowired private TypeTitle typeTitle;
  private Long nonExistingId;
  private Long titlesId;
  private Long dependentId;

  @BeforeEach
  public void setup() {
    // simulações
    user = new UserEntity();
    user.setEmail("zemario@gmail.com");
    user.setPassword("123456");

    titlesId = 1L;
    nonExistingId = 2L;
    dependentId = 4L;

    LocalDate dateRegistration = LocalDate.of(2023, 07, 02);
    LocalDate dateRerence = LocalDate.of(2023, 01, 06);
    LocalDate datePayment = LocalDate.of(2023, 02, 03);
    LocalDate dueDate = LocalDate.of(2023, 01, 03);

    TitlesEntity title = new TitlesEntity();

    title.setName("Clear Industria e comercio Ltda");
    title.setValueTitle(valueTitle);
    title.setDescription("Industria de produtos de limpeza");
    title.setDateRegistration(dateRegistration);
    title.setDateReference(dateRerence);
    title.setDatePayment(datePayment);
    title.setDueDate(dueDate);
    title.setObservation("teste");
    title.setUserEntity(user);
    title.setTypeTitle(typeTitle);
    title.setCostCenter(costCenter);
  }

  @Test
  void findAllShouldFindAllThenReturnListEmpty() {
    user = new UserEntity();

    when(repository.findAll()).thenReturn(Collections.emptyList());
    List<TitlesEntity> titlesEntityList = repository.findAll();

    assertTrue(titlesEntityList.isEmpty());
    assertEquals(0, titlesEntityList.size());
  }

  @Test
  void findAllShouldThenReturnTitleList() {

    UserDetails userDetails = new UserEntity();
    String token = jwtUtil.gerarToken((Authentication) userDetails);

    title = new TitlesEntity();
    TitlesEntity titles = new TitlesEntity();

    List<TitlesEntity> value1 = new java.util.ArrayList<>();
    value1.add(title);
    value1.add(titles);

    when(repository.findByUserEntity(any())).thenReturn(value1);
    when(repository.findAll()).thenReturn(value1);
    List<TitlesResponse> listTitle = service.findAll();
    assertNotNull(listTitle);
    assertEquals(2, listTitle.size());
  }

  @Test
  void shouldFindTitleWhenNotExistId() {
    user = new UserEntity();
    TitlesEntity title = new TitlesEntity();
    when(repository.findById(any())).thenReturn(Optional.of(title));
  }

  @Test
  void shouldSalveWhenNewTitle() {

    user = new UserEntity();
    TitlesEntity title = new TitlesEntity();

    when(repository.save(any(TitlesEntity.class))).thenReturn(title);
    verify(repository, times(1)).save(title);
  }

  @Test
  void shouldUpdateTitle() {

    user = new UserEntity();
    when(repository.findByUserEntity(any())).thenReturn((List<TitlesEntity>) user);

    when(repository.findById(any())).thenReturn(Optional.of(title));
    when(repository.save(any(TitlesEntity.class))).thenReturn(title);

    TitlesResponse updateTitle = service.update(titlesId, new TitlesRequestDTO());

    verify(repository, times(1)).findById(titlesId);
    verify(repository, times(1)).save(title);

    assertNotNull(updateTitle);
    assertEquals(title.getTitlesId(), updateTitle.getTitlesId());
    assertEquals(title.getName(), updateTitle.getName());
    assertEquals(title.getValueTitle(), updateTitle.getValueTitle());
    assertEquals(title.getDateRegistration(), updateTitle.getDateRegistration());
    assertEquals(title.getDateReference(), updateTitle.getDateReference());
    assertEquals(title.getDatePayment(), updateTitle.getDatePayment());
    assertEquals(title.getDueDate(), updateTitle.getDueDate());
    assertEquals(title.getDescription(), updateTitle.getDescription());
    assertEquals(title.getTypeTitle(), updateTitle.getTypeTitle());
  }

  @Test
  public void deleteShouldDoNothingWhenIdExistis() {
    assertDoesNotThrow(
        () -> {
          service.delete(titlesId);
        });
    // verfica se foi realizada alguma chamada...
    verify(repository, times(1)).deleteById(titlesId);
  }

  @Test
  public void deleteShouldThrowDataBaseExceptionWhenDepentIdExistis() {
    assertThrows(
        ResourceNotFoundException.class,
        () -> {
          service.delete(dependentId);
        });
    // verfica se foi realizada alguma chamada...
    verify(repository, times(1)).deleteById(dependentId);
  }

  @Test
  public void deleteShouldThrowResourceNotFoundExceptionWhenIdExistis() {
    assertThrows(
        ResourceNotFoundException.class,
        () -> {
          service.delete(nonExistingId);
        });
    // verfica se foi realizada alguma chamada...
    verify(repository, times(1)).deleteById(nonExistingId);
  }

  @Test
  void getCashFlowByDueDate() {}
}

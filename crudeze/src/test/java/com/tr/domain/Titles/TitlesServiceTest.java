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
import com.tr.domain.exception.DatabaseException;
import com.tr.domain.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

@ExtendWith(MockitoExtension.class)
class TitlesServiceTest {

  @InjectMocks private TitlesService service;
  @Mock private TitlesRepository repository;

  private UserEntity userEntity;
  private List<CostCenterEntity> costCenter;
  private BigDecimal valueTitle = BigDecimal.valueOf(3056.20);
  private TitlesEntity title;
  private TypeTitle typeTitle;
  private Long nonExistingId;
  private Long titlesId;
  private Long dependentId;

  @BeforeEach
  public void setup() {
    //simulações
    userEntity = new UserEntity();

    titlesId = 1L;
    nonExistingId = 2L;
    dependentId = 4L;

    LocalDate dateRegistration = LocalDate.of(2023, 07, 02);
    LocalDate dateRerence = LocalDate.of(2023, 01, 06);
    LocalDate datePayment = LocalDate.of(2023, 02, 03);
    LocalDate dueDate = LocalDate.of(2023, 01, 03);

     // Comportamentos para objetos mokados
    // Quando encontra o titleId não retorna nada
    doNothing().when(repository).deleteById(titlesId);

    // Quando não encontra ou o titleId que não existe deve retornar uma exception
    doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);

    //Quando tenta excluir um id que depender de outra entidade
    doThrow(DatabaseException.class).when(repository).deleteById(dependentId);

    when(repository.findAll()).thenReturn(Collections.emptyList());
    when(repository.save(any())).thenReturn(title);
    //quando o id exist
    //when(repository.findById(titlesId)).thenReturn(Optional.of(title));

    //quando o id não exist
    when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
  }

  @Test
  void findAllShouldFindAllThenReturnListEmpty() {
    userEntity = new UserEntity();
    when(repository.findAll()).thenReturn(Collections.emptyList());
    List<TitlesEntity> titlesEntityList = repository.findAll();
    assertTrue(titlesEntityList.isEmpty());
    assertEquals(0, titlesEntityList.size());
  }

  @Test
  void shouldTitleWhenFindById() {
    userEntity = new UserEntity();
    when(repository.findById(any())).thenReturn(Optional.of(title));
    TitlesResponse savedTitle = service.findById(titlesId);
    assertNotNull(savedTitle);
    assertEquals(title.getTitlesId(), savedTitle.getTitlesId());
  }

  @Test
  void salveShouldWhen() {


  }

  @Test
  void updateShouldTitleWhwen() {

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
    title.setUserEntity(userEntity);
    title.setTypeTitle(typeTitle);
    title.setCostCenter(costCenter);

    when(repository.findById(titlesId)).thenReturn(Optional.of(title));
    when(repository.findByUserEntity(any(UserEntity.class))).thenReturn(
        (List<TitlesEntity>) userEntity);
    when(repository.save(any(TitlesEntity.class))).thenReturn(title);
    TitlesResponse updateTitle = service.update(titlesId, new TitlesRequestDTO() );

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
    Mockito.verify(repository, times(1)).deleteById(titlesId);
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

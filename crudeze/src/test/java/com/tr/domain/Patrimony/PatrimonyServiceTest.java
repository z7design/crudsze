package com.tr.domain.Patrimony;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PatrimonyServiceTest {

  @Mock private PatrimonyRepository repository;

  @InjectMocks private PatrimonyService service;

  private PatrimonyEntity patrimony;
  private Long patrimonyId;

  private LocalDate DateOfPurchase;

  @BeforeEach
  public void setup() {
    DateOfPurchase = LocalDate.of(2007, 07, 02);

    patrimony = new PatrimonyEntity();
    patrimony.setCode(12);
    patrimony.setLocation("Predio 1");
    patrimony.setLocation("Descrição");
    patrimony.setPurchaseInvoice("25463");
    patrimony.setDateOfPurchase(DateOfPurchase);
    patrimony.setAccountingClassification("Bens e Serviços");
  }

  @Test
  void shouldCreatePatrimony() {

    when(repository.save(any(PatrimonyEntity.class))).thenReturn(patrimony);
    PatrimonyEntity savedPatrimony = service.createPatrimony(patrimony);
    assertNotNull(savedPatrimony);
    assertEquals(12, savedPatrimony.getCode());
  }

  @Test
  void findPatrimonyById() {
    when(repository.findById(any())).thenReturn(Optional.of(patrimony));
    PatrimonyEntity savedPatrimony = service.findPatrimonyById(patrimonyId);
    assertNotNull(savedPatrimony);
    assertEquals(patrimony.getPatrimonyId(), savedPatrimony.getPatrimonyId());
  }

  @Test
  void updatePatrimony() {

    when(repository.findById(any())).thenReturn(Optional.of(patrimony));

    patrimony.setPatrimonyId(patrimonyId);
    patrimony.setCode(12);
    patrimony.setLocation("Predio 2");
    patrimony.setDateOfPurchase(DateOfPurchase);
    patrimony.setPurchaseInvoice("25463");
    patrimony.setDescription("Mesa para escritório");
    patrimony.setAccountingClassification("Bens");

    when(repository.save(patrimony)).thenReturn(patrimony);
    PatrimonyEntity patrimonyEntity = service.updatePatrimony(patrimony);

    assertNotNull(patrimonyEntity);
    assertEquals(patrimony.getPatrimonyId(), patrimonyEntity.getPatrimonyId());
    assertEquals(patrimony.getCode(), patrimonyEntity.getCode());
    assertEquals(patrimony.getDescription(), patrimonyEntity.getDescription());
    assertEquals(patrimony.getLocation(), patrimonyEntity.getLocation());
    assertEquals(patrimony.getDateOfPurchase(), patrimonyEntity.getDateOfPurchase());
    assertEquals(patrimony.getPurchaseInvoice(), patrimonyEntity.getPurchaseInvoice());
    assertEquals(patrimony.getAccountingClassification(), patrimonyEntity.getAccountingClassification());
  }

  @Test
  void deletePatrimony() {
        assertDoesNotThrow(
        () -> {
          service.deletePatrimony(patrimonyId);
        });
    verify(repository).deleteById(patrimonyId);
  }

  @Test
  void findAllByPatrimony() {
 PatrimonyEntity patrimony1 = new PatrimonyEntity();

    List<PatrimonyEntity> value1 = new java.util.ArrayList<>();
    value1.add(patrimony);
    value1.add(patrimony1);

    when(repository.findAll()).thenReturn(value1);
    List<PatrimonyEntity> savedPatrimony = service.findAllByPatrimony();
    assertNotNull(savedPatrimony);
    assertEquals(2, savedPatrimony.size());
  }

  @Test
  void shouldFindAllThenReturnEmpty() {
    when(repository.findAll()).thenReturn(Collections.emptyList());
    List<PatrimonyEntity> listPatrimony = repository.findAll();
    assertTrue(listPatrimony.isEmpty());
    assertEquals(0, listPatrimony.size());
  }
}

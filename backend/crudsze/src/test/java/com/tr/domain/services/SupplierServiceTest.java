package com.tr.domain.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.tr.domain.entities.Address;
import com.tr.domain.entities.Supplier;
import com.tr.domain.repositories.SupplierRepository;
import java.math.BigDecimal;
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
public class SupplierServiceTest {

  @InjectMocks private SupplierService service;
  @Mock private SupplierRepository repository;
  @Mock private DocumentService documentService;

  private Long supplierId = 1L;
  private Supplier supplier;
  private Address address;
  private BigDecimal value = BigDecimal.valueOf(150.000);

  @BeforeEach
  public void setup() {
    LocalDate dateRegistration = LocalDate.of(2023, 07, 20);
    LocalDate dateOfLastPurchase = LocalDate.of(2023, 07, 21);

    Supplier supplier =
        new Supplier(
            "Clear Industria e comercia Ltda",
            "31873559000150",
            "Olympus Turismo Ltda",
            "Wanderson",
            "OlympusTur",
            "3095442146449",
            "3095442146449",
            "3135117899",
            "31998555677",
            dateRegistration,
            dateOfLastPurchase,
            address);
  }

  @Test
  public void shouldVeicleWhenThenSalveVeicles() {

    LocalDate dateRegistration = LocalDate.of(2023, 07, 20);
    LocalDate dateOfLastPurchase = LocalDate.of(2023, 07, 21);

    new Supplier(
        "Clear Industria e comercia Ltda",
        "31873559000150",
        "Olympus Turismo Ltda",
        "Wanderson",
        "OlympusTur",
        "3095442146449",
        "3095442146449",
        "3135117899",
        "31998555677",
        dateRegistration,
        dateOfLastPurchase,
        address);

    when(repository.save(any(Supplier.class))).thenReturn(supplier);
    var savedSupplier = service.createSupplier(supplier);
    assertEquals(savedSupplier, savedSupplier);
  }

  @Test
  void shouldFindAllBySupplier() {

    LocalDate dateRegistration = LocalDate.of(2023, 07, 20);
    LocalDate dateOfLastPurchase = LocalDate.of(2023, 07, 21);

    new Supplier(
        "Clear Industria e comercia Ltda",
        "31873559000150",
        "Olympus Turismo Ltda",
        "Wanderson",
        "OlympusTur",
        "3095442146449",
        "3095442146449",
        "3135117899",
        "31998555677",
        dateRegistration,
        dateOfLastPurchase,
        address);

    Supplier supplier1 =
        new Supplier(
            "Clear Industria e comercia Ltda",
            "31873559000150",
            "Olympus Turismo Ltda",
            "Wanderson",
            "OlympusTur",
            "3095442146449",
            "3095442146449",
            "3135117899",
            "31998555677",
            dateRegistration,
            dateOfLastPurchase,
            address);

    when(repository.findAll()).thenReturn(List.of(supplier, supplier1));
    List<Supplier> listSupplier = service.findAllBySupplier();
    assertNotNull(listSupplier);
    assertEquals(2, listSupplier.size());
  }

  @Test
  void shouldFindAllThenReturnEmptySupplier() {
    when(repository.findAll()).thenReturn(Collections.emptyList());
    List<Supplier> listSupplier = repository.findAll();
    assertTrue(listSupplier.isEmpty());
    assertEquals(0, listSupplier.size());
  }

  @Test
  void shouldFindByIdSupplier() {

    LocalDate dateRegistration = LocalDate.of(2023, 07, 20);
    LocalDate dateOfLastPurchase = LocalDate.of(2023, 07, 21);

    final var supplier =
        new Supplier(
            "Clear Industria e comercia Ltda",
            "31873559000150",
            "Olympus Turismo Ltda",
            "Wanderson",
            "OlympusTur",
            "3095442146449",
            "3095442146449",
            "3135117899",
            "31998555677",
            dateRegistration,
            dateOfLastPurchase,
            address);

    when(repository.findById(any())).thenReturn(Optional.ofNullable(supplier));
    Supplier savedSupplier = service.findSupplierById(supplierId);
    assertNotNull(savedSupplier);
    assertEquals(supplier.getSupplierId(), savedSupplier.getSupplierId());
  }

  @Test
  void ShoulUpdateSupplier() {
    LocalDate dateRegistration = LocalDate.of(2023, 07, 20);
    LocalDate dateOfLastPurchase = LocalDate.of(2023, 07, 21);

    final var supplier =
        new Supplier(
            "Clear Industria e comercia Ltda",
            "31873559000150",
            "Olympus Turismo Ltda",
            "Wanderson",
            "OlympusTur",
            "teste@gmail.com",
            "3095442146449",
            "3135117899",
            "31998555677",
            dateRegistration,
            dateOfLastPurchase,
            address);

    when(repository.findById(any())).thenReturn(Optional.of(supplier));
    
    supplier.setName("Clear Industria e comercia Ltda");
    supplier.setCnpj("31873559000150");
    supplier.setCorpareteName("Olympus Turismo Ltda");
    supplier.setFantasyName("Teste 2");
    supplier.setResponsable("Jão Rodolfo");
    supplier.setEmail("jaorodolfo@gmail.com");
    supplier.setPhone("3135117899");
    supplier.setCelphone("3135117899");
    supplier.setMunicipalRegistration("31998555677");
    supplier.setDateOfLastPurchase(dateOfLastPurchase);
    supplier.setDateRegistration(dateRegistration);
    
    when(repository.save(supplier)).thenReturn(supplier);
    Supplier updateSupplier = service.updateSupplier(supplier);

    assertNotNull(updateSupplier);
    assertEquals(supplier.getSupplierId(), updateSupplier.getSupplierId());
    assertEquals(supplier.getName(), updateSupplier.getName());
    assertEquals(supplier.getCnpj(), updateSupplier.getCnpj());
    assertEquals(supplier.getDateRegistration(), updateSupplier.getDateRegistration());
    assertEquals(supplier.getDateOfLastPurchase(), updateSupplier.getDateOfLastPurchase());
    assertEquals(supplier.getCelphone(), updateSupplier.getCelphone());
    assertEquals(supplier.getCorpareteName(), updateSupplier.getCorpareteName());
    assertEquals(supplier.getFantasyName(), updateSupplier.getFantasyName());
    assertEquals(supplier.getMunicipalRegistration(), updateSupplier.getMunicipalRegistration());
    assertEquals(supplier.getEmail(), updateSupplier.getEmail());
    assertEquals(supplier.getResponsable(), updateSupplier.getResponsable());
    assertEquals(supplier.getPhone(), updateSupplier.getPhone());
    assertEquals(supplier.getAddress(), updateSupplier.getAddress());
  }

  @Test
  public void souldDeleteDoNothingWhenIdExistsSupplier() {
    assertDoesNotThrow(
        () -> {
          service.deleteSupplier(supplierId);
        });
    verify(repository).deleteById(supplierId);
  }
}

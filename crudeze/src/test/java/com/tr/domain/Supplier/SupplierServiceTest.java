package com.tr.domain.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.tr.domain.Address.AddressEntity;
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

  @InjectMocks
  private SupplierService service;
  @Mock
  private SupplierRepository repository;

  private Long supplierId = 1L;
  @InjectMocks
  private SupplierEntity supplier;
  private AddressEntity address;
  private BigDecimal value = BigDecimal.valueOf(150.000);

  @BeforeEach
  public void setup() {
    LocalDate dateRegistration = LocalDate.of(2023, 07, 20);
    LocalDate dateOfLastPurchase = LocalDate.of(2023, 07, 21);

  supplier = new SupplierEntity(supplierId,
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
  public void shouldVeicleWhenThenSalveSupplier() {

    LocalDate dateRegistration = LocalDate.of(2023, 07, 20);
    LocalDate dateOfLastPurchase = LocalDate.of(2023, 07, 21);

    supplier = new SupplierEntity();

    when(repository.save(any(SupplierEntity.class))).thenReturn(supplier);
    SupplierEntity savedSupplier = service.createSupplier(supplier);
    assertEquals(supplier, savedSupplier);
  }

  @Test
  void shouldFindAllBySupplier() {

    LocalDate dateRegistration = LocalDate.of(2023, 07, 20);
    LocalDate dateOfLastPurchase = LocalDate.of(2023, 07, 21);
    
   supplier =
        new SupplierEntity(supplierId,
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

    List<SupplierEntity> value1 = new java.util.ArrayList<>();
    value1.add(supplier);
      when(repository.findAll()).thenReturn(value1);
    List<SupplierEntity> listSupplier = service.getAllBySupplier(1, 1000);
    assertNotNull(listSupplier);
    assertEquals(1, listSupplier.size());
  }

  @Test
  void shouldFindAllThenReturnEmptySupplier() {
    when(repository.findAll()).thenReturn(Collections.emptyList());
    List<SupplierEntity> listSupplier = repository.findAll();
    assertTrue(listSupplier.isEmpty());
    assertEquals(0, listSupplier.size());
  }

  @Test
  void shouldFindByIdSupplier() {

    LocalDate dateRegistration = LocalDate.of(2023, 07, 20);
    LocalDate dateOfLastPurchase = LocalDate.of(2023, 07, 21);

  supplier =
        new SupplierEntity(supplierId,
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
    SupplierEntity savedSupplier = service.findSupplierById(supplierId);
    assertNotNull(savedSupplier);
    assertEquals(supplier.getSupplierId(), savedSupplier.getSupplierId());
  }

  @Test
  void ShoulUpdateSupplier() {
    LocalDate dateRegistration = LocalDate.of(2023, 07, 20);
    LocalDate dateOfLastPurchase = LocalDate.of(2023, 07, 21);

       supplier =
        new SupplierEntity(supplierId,
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
    SupplierEntity updateSupplier = service.updateSupplier(supplier);

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

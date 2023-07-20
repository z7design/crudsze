package com.tr.domain.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.tr.domain.entities.Veicle;
import com.tr.domain.repositories.VeicleRepository;
import java.math.BigDecimal;
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
public class VeicleServiceTest {

  @InjectMocks private VeicleService service;
  @Mock private VeicleRepository repository;

  private Long veicleId = 1L;
  private Veicle veicle;
  private BigDecimal value = BigDecimal.valueOf(150.000);

  @BeforeEach
  public void setup() {
    Veicle veicle = new Veicle(veicleId, "GRT-7898", "Corolla", "Red", value);
  }

  @Test
  public void shouldVeicleWhenThenSalveVeicles() {
    veicle = new Veicle(veicleId, "GRT-7898", "Corolla", "Red", value);

    when(repository.save(any(Veicle.class))).thenReturn(veicle);
    Veicle savedCat = service.createVeicle(veicle);
    assertEquals(veicle, savedCat);
  }

  @Test
  void shouldFindAllByCategories() {
    veicle = new Veicle(veicleId, "GRT-7898", "Corolla", "Red", value);
    Veicle veicle1 = new Veicle(veicleId, "GRT-7898", "Corolla", "Red", value);

    when(repository.findAll()).thenReturn(List.of(veicle, veicle1));
    List<Veicle> listVeicle = service.findAllByVeicle();
    assertNotNull(listVeicle);
    assertEquals(2, listVeicle.size());
  }

  @Test
  void shouldFindAllThenReturnEmptyCategories() {
    when(repository.findAll()).thenReturn(Collections.emptyList());
    List<Veicle> listVeicle = repository.findAll();
    assertTrue(listVeicle.isEmpty());
    assertEquals(0, listVeicle.size());
  }

  @Test
  void shouldFindByIdViecle() {
    final var veicle = new Veicle(veicleId, "GRT-7898", "Corolla", "Red", value);
    when(repository.findById(any())).thenReturn(Optional.ofNullable(veicle));
    Veicle savedVeicle = service.findVeicleById(veicleId);
    assertNotNull(savedVeicle);
    assertEquals(veicle.getVeicleId(), savedVeicle.getVeicleId());
  }

  @Test
  void ShoulUpdateVeicle() {
    final var veicle = new Veicle(veicleId, "GRT-7898", "Corolla", "Red", value);
    when(repository.findById(any())).thenReturn(Optional.of(veicle));

    veicle.setModel("Corolla");
    veicle.setPlate("GHF-2546");
    veicle.setColor("Red");
    veicle.setValue(value);

    when(repository.save(veicle)).thenReturn(veicle);
    Veicle updateVeicle = service.update(veicle);

    assertNotNull(updateVeicle);
    assertEquals(veicle.getVeicleId(), updateVeicle.getVeicleId());
    assertEquals(veicle.getPlate(), updateVeicle.getPlate());
    assertEquals(veicle.getModel(), updateVeicle.getModel());
    assertEquals(veicle.getColor(), updateVeicle.getColor());
    assertEquals(veicle.getValue(), updateVeicle.getValue());
  }

  @Test
  public void deleteShouldDoNothingWhenIdExists() {
    assertDoesNotThrow(
        () -> {
          service.deleteVeicle(veicleId);
        });
    verify(repository).deleteById(veicleId);
  }
}

package com.tr.domain.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.tr.domain.entities.Document;
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
  @Mock private DocumentService documentService;

  private Long veicleId = 1L;
  private Veicle veicle;
  private Document document;
  private BigDecimal value = BigDecimal.valueOf(150.000);

  @BeforeEach
  public void setup() {
    Veicle veicle = new Veicle(veicleId, "GRT-7898", "Corolla", "Red", value, document);
  }

  @Test
  public void shouldVeicleWhenThenSalveVeicles() {
    veicle = new Veicle(veicleId, "GRT-7898", "Corolla", "Red", value, document);
    when(repository.save(any(Veicle.class))).thenReturn(veicle);
    var savedVeic = service.createVeicle(veicle);
    assertEquals(veicle, savedVeic);
  }

  @Test
  void shouldFindAllByCategories() {
    veicle = new Veicle(veicleId, "GRT-7898", "Corolla", "Red", value, document);
    Veicle veicle1 = new Veicle(veicleId, "GRT-7898", "Corolla", "Red", value, document);

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
    final var veicle = new Veicle(veicleId, "GRT-7898", "Corolla", "Red", value, document);
    when(repository.findById(any())).thenReturn(Optional.ofNullable(veicle));
    Veicle savedVeicle = service.findVeicleById(veicleId);
    assertNotNull(savedVeicle);
    assertEquals(veicle.getVeicleId(), savedVeicle.getVeicleId());
  }

  @Test
  void ShoulUpdateVeicle() {
    final var veicle = new Veicle(veicleId, "GRT-7898", "Corolla", "Red", value, document);
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

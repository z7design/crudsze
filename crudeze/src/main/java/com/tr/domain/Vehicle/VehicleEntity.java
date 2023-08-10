package com.tr.domain.Vehicle;

import com.tr.domain.Document.DocumentEntity;
import java.math.BigDecimal;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicles")
public class VehicleEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "vehicle_id")
  private Long vehicleId;

  @Column(name = "plate")
  private String plate;

  @Column(name = "color")
  private String color;

  @Column(name = "model")
  private String model;

  @Column(name = "value")
  private BigDecimal value;

  @ManyToOne
  @JoinColumn(nullable = false)
  private DocumentEntity document;
}

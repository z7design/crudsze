package com.tr.domain.Vehicle;

import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequest {
  private Long vehicleId;
  private String plate;
  private String color;
  private String model;
  private BigDecimal value;
}

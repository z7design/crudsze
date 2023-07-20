package com.tr.api.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VeicleResponse {
  @Schema(type = "string", format = "long", description = "Id of the Veicle.", example = "1")
  private Long veicleId;

  @Schema(type = "string", description = "Name plate", example = "Name the One plate")
  private String plate;

  @Schema(type = "string", description = "color", example = "Name the One color")
  private String color;

  @Schema(type = "string", description = "model", example = "Name the One model")
  private String model;

  @Schema(type = "string", description = "Value the veicle", example = "Value of the purchase")
  private BigDecimal value;
}

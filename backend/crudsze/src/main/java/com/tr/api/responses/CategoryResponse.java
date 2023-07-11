package com.tr.api.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
  @Schema(
      type = "string",
      format = "uuid",
      description = "Id of the Category.",
      example = "68e917ff-da7e-4863-bad9-cf7ad7c7229a")
  private UUID categoryId;

  @Schema(type = "string", description = "Name Category", example = "Name the One category")
  private String name;

  @Schema(
      type = "string",
      description = "Category description",
      example = "Category of the payments")
  private String description;
}

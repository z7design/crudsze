package com.tr.api.Check;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
  @Schema(type = "string", format = "long", description = "Id of the Category.", example = "1")
  private Long categoryId;

  @Schema(type = "string", description = "Name Category", example = "Name the One category")
  private String name;

  @Schema(
      type = "string",
      description = "Category description",
      example = "Category of the payments")
  private String description;
}

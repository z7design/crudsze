package com.tr.api.mappers;

import com.tr.api.responses.VeicleResponse;
import com.tr.domain.entities.Veicle;
import com.tr.domain.request.VeicleRequest;
import org.springframework.stereotype.Component;

@Component
public class VeicleMapper {
  public VeicleResponse toResponse(final Veicle entity) {
    return VeicleResponse.builder()
        .veicleId(entity.getVeicleId())
        .plate(entity.getPlate())
        .color(entity.getColor())
        .model(entity.getModel())
        .value(entity.getValue())
        .build();
  }
  
   public Veicle toEntity(final VeicleRequest veicleRequest) {
    return Veicle.builder()
        .plate(veicleRequest.getPlate())
        .model(veicleRequest.getModel())
        .color(veicleRequest.getColor())
        .value(veicleRequest.getValue())
        .build();
  }

}

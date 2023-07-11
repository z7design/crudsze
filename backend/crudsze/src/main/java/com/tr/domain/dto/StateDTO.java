package com.tr.domain.dto;

import com.tr.domain.entities.State;
import java.util.UUID;
import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StateDTO {
    @Column(name = "state_id", updatable = false, nullable = false)
    public UUID stateId;

    @Column(name = "name", updatable = false, nullable = false)
    private String name;
  
    @Column(name = "sigle", updatable = false, nullable = false)
    private String sigle;
    
    public StateDTO(State state){
      this.name = state.getName();
      this.sigle = state.getSigle();
    }
}

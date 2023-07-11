package com.tr.domain.dto;

import com.tr.domain.entities.City;
import com.tr.domain.entities.State;
import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO implements Serializable {
  
    private UUID cityId;
    
    private String name;
    
    private State state;
    
    public CityDTO(City city){
      this.name = city.getName();
      this.state = city.getState();
    }
}

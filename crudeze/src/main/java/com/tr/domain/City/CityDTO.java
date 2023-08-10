package com.tr.domain.City;

import com.tr.domain.State.StateEntity;
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
    
    private StateEntity state;
    
    public CityDTO(CityEntity city){
      this.name = city.getName();
      this.state = city.getState();
    }
}

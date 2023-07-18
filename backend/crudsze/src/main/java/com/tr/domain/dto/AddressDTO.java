package com.tr.domain.dto;

import com.tr.domain.enums.Nickname;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
  
  private Long addressId;
  private Nickname neighborhood;
  
}

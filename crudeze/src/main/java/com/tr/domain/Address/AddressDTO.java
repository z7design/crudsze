package com.tr.domain.Address;

import com.tr.domain.Enums.Nickname;
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

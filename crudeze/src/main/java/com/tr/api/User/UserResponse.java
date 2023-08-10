package com.tr.api.User;

import java.util.Date;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
  private Long userId;
  private String name;
  private String email;
  private String foto;
  private Date dateInativation;
  private Date dateRestiration;

}

package com.tr.domain.Address;

import com.tr.domain.City.CityEntity;
import com.tr.domain.Enums.Nickname;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class AddressEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "address_id")
  private Long addressId;

  @Column(name = "postal_code")
  private Integer postalCode;

  @Column(name = "public_place")
  private String publicPlace;

  @Column(name = "new_post_code")
  private Integer newCodePostal;

  @Column(name = "number")
  private Integer number;

  @Column(name = "complement")
  private String complement;

  @Column(name = "neighborhood")
  @Enumerated(EnumType.STRING)
  // @Convert(converter = NicknameConverter.class)
  private Nickname neighborhood;

  @ManyToOne
  @JoinColumn(nullable = false)
  private CityEntity city;
}

package com.tr.domain.City;

import com.tr.domain.State.StateEntity;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "city")
public class CityEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "city_id")
  private Long cityId;

  @Column(name = "name")
  private String name;

  @ManyToOne
  @JoinColumn(nullable = false)
  private StateEntity state;
}

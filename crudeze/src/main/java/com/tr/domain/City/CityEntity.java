package com.tr.domain.City;

import com.tr.domain.State.StateEntity;
import javax.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "city")
public class CityEntity {

  @Id
  @EmbeddedId
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "city_id")
  private Long cityId;

  @Column(name = "name")
  private String name;

  @ManyToOne
  @JoinColumn(nullable = false)
  private StateEntity state;
}

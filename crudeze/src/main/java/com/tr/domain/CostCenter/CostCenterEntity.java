package com.tr.domain.CostCenter;

import com.tr.domain.User.UserEntity;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cost_center")
public class CostCenterEntity {
  @Id
  @EmbeddedId
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "cost_center_id")
  private Long costCenterId;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "observation", columnDefinition = "Text")
  private String observation;

  @Column(name = "code")
  private Integer code;

  @ManyToOne()
  @JoinColumn(name = "user_id")
  private UserEntity userEntity;

}

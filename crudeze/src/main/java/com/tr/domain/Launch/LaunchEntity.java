package com.tr.domain.Launch;

import com.tr.domain.AccountPayble.AccountPaybleEntity;
import com.tr.domain.AccountReceive.AccountReceiveEntity;
import com.tr.domain.Enums.StastusLaunch;
import com.tr.domain.Enums.TypeLaunch;
import java.math.BigDecimal;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "launch")
public class LaunchEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "launch_id")
  private Long LaunchId;

  @Column(name = "value")
  private BigDecimal value;

  @Column(name = "tax")
  private BigDecimal tax;

  @Column(name = "type_launch")
  @Enumerated(EnumType.STRING)
  private TypeLaunch typeLaunch;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private StastusLaunch status;

  @ManyToOne
  @JoinColumn(nullable = false)
  private AccountPaybleEntity accountPayble;

  @ManyToOne
  @JoinColumn(nullable = false)
  private AccountReceiveEntity accountReceive;
}


package com.tr.domain.Maintenance;

import com.tr.domain.Enums.TypeMaintenance;
import com.tr.domain.Vehicle.VehicleEntity;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "maintenance")
public class MaintenanceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "maintenance_id")
  private Long maintenanceId;

  @Column(name = "km_initial")
  private String kmInitial;

  @Column(name = "km_finish")
  private String kmFinish;

  @Column(name = "description")
  private String description;

  @Column(name = "date_initial")
  private String dateInitial;

  @Column(name = "date_finish")
  private String dateFinish;

  @Column(name = "type_maintenance")
  @Enumerated(EnumType.STRING)
  private TypeMaintenance typeMaintenance;

  @ManyToOne
  @JoinColumn(nullable = true)
  private VehicleEntity vehicle;
  

}


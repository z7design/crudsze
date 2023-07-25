package com.tr.domain.entities;

import com.tr.domain.enums.TypeMaintenance;
import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "maintenance")
public class Maintenance {

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
  // @Convert(converter = TypeMaintenance.class)
  private TypeMaintenance typeMaintenance;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Vehicle vehicle;

  public Maintenance() {}

  public Maintenance(
      Long maintenanceId,
      String kmInitial,
      String kmFinish,
      String dateInitial,
      String dateFinish,
      String description,
      TypeMaintenance typeMaintenance,
      Vehicle vehicle) {
    this.maintenanceId = maintenanceId;
    this.kmInitial = kmInitial;
    this.kmFinish = kmFinish;
    this.description = description;
    this.dateInitial = dateInitial;
    this.dateFinish = dateFinish;
    this.typeMaintenance = typeMaintenance;
    this.vehicle = vehicle;
  }

  public Maintenance(
      String kmInitial,
      String kmFinish,
      String dateInitial,
      String dateFinish,
      String description,
      TypeMaintenance typeMaintenance,
      Vehicle vehicle) {
    this.kmInitial = kmInitial;
    this.kmFinish = kmFinish;
    this.description = description;
    this.dateInitial = dateInitial;
    this.dateFinish = dateFinish;
    this.typeMaintenance = typeMaintenance;
    this.vehicle = vehicle;
  }

  public Long getMaintenanceId() {
    return maintenanceId;
  }

  public void setMaintenanceId(Long maintenanceId) {
    this.maintenanceId = maintenanceId;
  }

  public String getKmInitial() {
    return kmInitial;
  }

  public void setKmInitial(String kmInitial) {
    this.kmInitial = kmInitial;
  }

  public String getKmFinish() {
    return kmFinish;
  }

  public void setKmFinish(String kmFinish) {
    this.kmFinish = kmFinish;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDateInitial() {
    return dateInitial;
  }

  public void setDateInitial(String dateInitial) {
    this.dateInitial = dateInitial;
  }

  public String getDateFinish() {
    return dateFinish;
  }

  public void setDateFinish(String dateFinish) {
    this.dateFinish = dateFinish;
  }

  public TypeMaintenance getTypeMaintenance() {
    return typeMaintenance;
  }

  public void setTypeMaintenance(TypeMaintenance typeMaintenance) {
    this.typeMaintenance = typeMaintenance;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Maintenance)) return false;
    Maintenance that = (Maintenance) o;
    return getMaintenanceId().equals(that.getMaintenanceId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getMaintenanceId());
  }
}

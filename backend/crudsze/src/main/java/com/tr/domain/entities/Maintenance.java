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

  @Column(name = "km")
  private String km;

  @Column(name = "description")
  private String description;

  @Column(name = "date_initial")
  private String dateInitial;

  @Column(name = "date_finish")
  private String dateFinish;

  @Column(name = "type_maintenance")
  @Enumerated(EnumType.STRING)
  //@Convert(converter = TypeMaintenance.class)
  private TypeMaintenance typeMaintenance;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Veicle veicle;

  public Maintenance() {}

  public Maintenance(Long manutencaoId, String km, String dateInitial, String dateFinish, TypeMaintenance typeMaintenance, Veicle veicle) {
    this.maintenanceId = manutencaoId;
    this.km = km;
    this.description = description;
    this.dateInitial = dateInitial;
    this.dateFinish = dateFinish;
    this.typeMaintenance = typeMaintenance;
    this.veicle = veicle;
  }
  
    public Maintenance(String km, String dateInitial, String dateFinish, TypeMaintenance typeMaintenance, Veicle veicle) {
    this.km = km;
    this.description = description;
    this.dateInitial = dateInitial;
    this.dateFinish = dateFinish;
    this.typeMaintenance = typeMaintenance;
    this.veicle = veicle;
  }

  public Long getManutencaoId() {
    return maintenanceId;
  }

  public void setManutencaoId(Long manutencaoId) {
    this.maintenanceId = manutencaoId;
  }

  public String getKm() {
    return km;
  }

  public void setKm(String km) {
    this.km = km;
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

  public Veicle getVeicle() {
    return veicle;
  }

  public void setVeicle(Veicle veicle) {
    this.veicle = veicle;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Maintenance))
      return false;
    Maintenance that = (Maintenance) o;
    return getManutencaoId().equals(that.getManutencaoId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getManutencaoId());
  }
}

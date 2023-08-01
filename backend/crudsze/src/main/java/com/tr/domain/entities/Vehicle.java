package com.tr.domain.entities;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "vehicles")
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "vehicle_id")
  private Long vehicleId;

  @Column(name = "plate")
  private String plate;

  @Column(name = "color")
  private String color;

  @Column(name = "model")
  private String model;

  @Column(name = "value")
  private BigDecimal value;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Document document;

  public Vehicle() {}

  public Vehicle(
      Long vehicleId,
      String plate,
      String model,
      String color,
      BigDecimal value,
      Document document) {
    this.plate = plate;
    this.color = color;
    this.model = model;
    this.value = value;
    this.document = document;
  }

  public Vehicle(String plate, String model, String color, BigDecimal value, Document document) {
    this.plate = plate;
    this.color = color;
    this.model = model;
    this.value = value;
    this.document = document;
  }

  public Long getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(Long vehicleId) {
    this.vehicleId = vehicleId;
  }

  public String getPlate() {
    return plate;
  }

  public void setPlate(String plate) {
    this.plate = plate;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public Document getDocument() {
    return document;
  }

  public void setDocument(Document document) {
    this.document = document;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Vehicle)) return false;
    Vehicle vehicle = (Vehicle) o;
    return getVehicleId().equals(vehicle.getVehicleId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getVehicleId());
  }
}

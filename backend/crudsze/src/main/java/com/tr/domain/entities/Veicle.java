package com.tr.domain.entities;

import java.math.BigDecimal;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "veicles")
public class Veicle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "veicle_id")
  private Long veicleId;

  @Column(name = "plate")
  private String plate;

  @Column(name = "color")
  private String color;

  @Column(name = "model")
  private String model;

  @Column(name = "value")
  private BigDecimal value;

  public Veicle() {}

  public Veicle(Long veicleId, String plate, String model, String color, BigDecimal value) {
    this.plate = plate;
    this.color = color;
    this.model = model;
    this.value = value;
  }

  public Veicle(String plate, String model, String color, BigDecimal value) {
    this.plate = plate;
    this.color = color;
    this.model = model;
    this.value = value;
  }

  public Long getVeicleId() {
    return veicleId;
  }

  public void setVeicleId(Long veicleId) {
    this.veicleId = veicleId;
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
}

package com.tr.domain.entities;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Table(name = "phones")
public class Phone {
  @Id
  @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "UUIDGenerator")
  @Column(name = "phone_id")
  private UUID phoneId;

  @Column(name = "ddd")
  private String ddd;

  @Column(name = "ddi")
  private String ddi;

  @Column(name = "number")
  private Integer number;

  public Phone() {}

  public Phone(String name, String ddd, String ddi, Integer number) {
    this.ddd = ddd;
    this.ddi = ddi;
    this.number = number;
  }

  public Phone(UUID phoneId, String name, String ddd, String ddi, Integer number) {
    this.phoneId = phoneId;
    this.ddd = ddd;
    this.ddi = ddi;
    this.number = number;
  }

  public UUID getPhoneId() {
    return phoneId;
  }

  public void setPhoneId(UUID phoneId) {
    this.phoneId = phoneId;
  }

  public String getDdd() {
    return ddd;
  }

  public void setDdd(String ddd) {
    this.ddd = ddd;
  }

  public String getDdi() {
    return ddi;
  }

  public void setDdi(String ddi) {
    this.ddi = ddi;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Phone)) return false;
    Phone phone = (Phone) o;
    return getPhoneId().equals(phone.getPhoneId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getPhoneId());
  }
}

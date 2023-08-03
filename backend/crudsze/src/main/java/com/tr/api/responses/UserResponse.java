package com.tr.api.responses;

import java.util.Date;

public class UserResponse {
  private Long userId;
  private String name;
  private String email;
  private String foto;
  private Date dateInativation;
  private Date dateRestiration;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFoto() {
    return foto;
  }

  public void setFoto(String foto) {
    this.foto = foto;
  }

  public Date getDateInativation() {
    return dateInativation;
  }

  public void setDateInativation(Date dateInativation) {
    this.dateInativation = dateInativation;
  }

  public Date getDateRestiration() {
    return dateRestiration;
  }

  public void setDateRestiration(Date dateRestiration) {
    this.dateRestiration = dateRestiration;
  }
}

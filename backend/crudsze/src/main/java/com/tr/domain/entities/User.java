package com.tr.domain.entities;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "name")
  private String name;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "foto", columnDefinition = "Text")
  private String foto;

  @Column(name = "date_registration")
  private Date dateRegistration;

  @Column(name = "date_inativation")
  private Date dateInativation;

  @OneToMany(mappedBy = "user")
  private List<Titles> titles;

  @Column(name = "password", nullable = false)
  private String password;

  public User(
      int userId,
      String name,
      String email,
      String foto,
      Date dateRegistration,
      Date dateInativation,
      String password) {}

  public User(
      String name,
      String email,
      String foto,
      Date dateRegistration,
      Date dateInativation,
      String password) {
    this.name = name;
    this.email = email;
    this.foto = foto;
    this.dateInativation = dateInativation;
    this.dateRegistration = dateRegistration;
    this.password = password;
  }

  public User(
      Long userId,
      String name,
      String email,
      String foto,
      Date dateRegistration,
      Date dateInativation,
      String password) {
    this.userId = userId;
    this.name = name;
    this.email = email;
    this.foto = foto;
    this.dateInativation = dateInativation;
    this.dateRegistration = dateRegistration;
    this.password = password;
  }

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

  public Date getDateRegistration() {
    return dateRegistration;
  }

  public void setDateRegistration(Date dateRegistration) {
    this.dateRegistration = dateRegistration;
  }

  public Date getDateInativation() {
    return dateInativation;
  }

  public void setDateInativation(Date dateInativation) {
    this.dateInativation = dateInativation;
  }

  public List<Titles> getTitles() {
    return titles;
  }

  public void setTitles(List<Titles> titles) {
    this.titles = titles;
  }

  @Override
  public String toString() {
    return "User{"
        + "userId="
        + userId
        + ", name='"
        + name
        + '\''
        + ", email='"
        + email
        + '\''
        + '}';
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return null;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}

package com.tr.domain.entities;

import java.util.UUID;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Table(name = "users")
public class User {
  @Id
  @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "UUIDGenerator")
  @Column(name = "user_id")
  private UUID userId;
  
  @Column(name = "name")
  private String name;
  
  @Column(name = "email")
  private String email;
  
  public User(){}
  
  public User(String name, String email){
    this.name = name;
    this.email = email;
  }

  public User(UUID userId, String name, String email){
    this.userId = userId;
    this.name = name;
    this.email = email;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
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
}

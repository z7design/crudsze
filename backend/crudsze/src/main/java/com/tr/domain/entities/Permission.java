package com.tr.domain.entities;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Table(name = "permission")
public class Permission {
  @Id
  @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
  @GeneratedValue(generator = "UUIDGenerator")
  @Column(name = "permission_id", updatable = false, nullable = false)
  private UUID permissionId;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;
  
  public Permission(){}
  
  public Permission(String name, String description){
    this.name = name;
    this.description = description;
  }

  public Permission(UUID permissionId,String name, String description){
    this.permissionId = permissionId;
    this.name = name;
    this.description = description;
  }

  public UUID getPermissionId() {
    return permissionId;
  }

  public void setPermissionId(UUID permissionId) {
    this.permissionId = permissionId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Permission))
      return false;
    Permission that = (Permission) o;
    return getPermissionId().equals(that.getPermissionId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getPermissionId());
  }
}

package com.tr.domain.entities;

import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "type_document")
public class TypeDocument {
  
   @Id 
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "type_document_id")
   private Long typeDocumentId;
   
   @Column(name = "description")
   private String description;
   
   public TypeDocument(){}
  
  public TypeDocument(Long typeDocumentId, String description){
     this.typeDocumentId = typeDocumentId;
     this.description = description;
  }
  
    public TypeDocument(String description){
     this.description = description;
  }

  public Long getTypeDocumentId() {
    return typeDocumentId;
  }

  public void setTypeDocumentId(Long typeDocumentId) {
    this.typeDocumentId = typeDocumentId;
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
    if (!(o instanceof TypeDocument))
      return false;
    TypeDocument that = (TypeDocument) o;
    return getTypeDocumentId().equals(that.getTypeDocumentId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTypeDocumentId());
  }
}

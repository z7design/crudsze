package com.tr.domain.entities;

import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "document")
public class Document {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "document_id")
  private Long documentId;

  @Column(name = "number")
  private Integer number;

  @Column(name = "description")
  private String description;

  @Column(name = "obligororiness")
  private String obligororiness;

  @Column(name = "governmentAgency")
  private String governmentAgency;

  @Column(name = "due_date")
  private Date dueDate;

  @ManyToOne
  @JoinColumn(nullable = false)
  private TypeDocument typeDocument;

  public Document() {}

  public Document(
      Long documentId,
      Integer number,
      String description,
      String obligororiness,
      String governmentAgency,
      Date dueDate,
      TypeDocument typeDocument) {
    this.documentId = documentId;
    this.number = number;
    this.description = description;
    this.obligororiness = obligororiness;
    this.governmentAgency = governmentAgency;
    this.dueDate = dueDate;
    this.typeDocument = typeDocument;
  }

  public Document(
      Integer number,
      String description,
      String obligororiness,
      String governmentAgency,
      Date dueDate,
      TypeDocument typeDocument) {
    this.number = number;
    this.description = description;
    this.obligororiness = obligororiness;
    this.governmentAgency = governmentAgency;
    this.dueDate = dueDate;
    this.typeDocument = typeDocument;
  }

  public Long getDocumentId() {
    return documentId;
  }

  public void setDocumentId(Long documentId) {
    this.documentId = documentId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getObligororiness() {
    return obligororiness;
  }

  public void setObligororiness(String obligororiness) {
    this.obligororiness = obligororiness;
  }

  public String getGovernmentAgency() {
    return governmentAgency;
  }

  public void setGovernmentAgency(String governmentAgency) {
    this.governmentAgency = governmentAgency;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public TypeDocument getTypeDocument() {
    return typeDocument;
  }

  public void setTypeDocument(TypeDocument typeDocument) {
    this.typeDocument = typeDocument;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Document)) return false;
    Document document = (Document) o;
    return getDocumentId().equals(document.getDocumentId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getDocumentId());
  }
}

package com.tr.domain.entities;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@Table(name = "document")
public class Document {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "document_id")
  private Long documentId;

  @Column(name = "number")
  private Integer number;

  @Column(name = "description")
  private String description;

  @Column(name = "obligororiness")
  private String obligororiness;

  @Column(name = "government_agency")
  private String governmentAgency;

  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @Column(name = "due_date")
  private LocalDate dueDate;

  public Document() {}

  public Document(
      Long documentId,
      Integer number,
      String description,
      String obligororiness,
      String governmentAgency,
      LocalDate dueDate) {
    this.documentId = documentId;
    this.number = number;
    this.description = description;
    this.obligororiness = obligororiness;
    this.governmentAgency = governmentAgency;
    this.dueDate = dueDate;
  }

  public Document(
      Integer number,
      String description,
      String obligororiness,
      String governmentAgency,
      LocalDate dueDate) {
    this.number = number;
    this.description = description;
    this.obligororiness = obligororiness;
    this.governmentAgency = governmentAgency;
    this.dueDate = dueDate;
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

  public LocalDate getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
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

package com.tr.domain.Document;

import java.time.LocalDate;
import javax.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "document")
public class DocumentEntity {

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
}


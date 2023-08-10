package com.tr.domain.Patrimony;

import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patrimony")
public class PatrimonyEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "patrimony_id")
  private Long patrimonyId;

  @Column(name = "code")
  private Integer code;

  @Column(name = "description")
  private String description;

  @Column(name = "location")
  private String location;

  @Column(name = "purchase_invoice")
  private String purchaseInvoice;

  @Column(name = "date_of_purchase")
  private Date dateOfPurchase;

  @Column(name = "accounting_classification")
  private String accountingClassification;
  
}

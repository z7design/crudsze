package com.tr.domain.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "account_eceive")
public class AccountReceive {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "account_receive_id")
  private Long accountReceiveId;

  @Column(name = "number_document")
  private Integer numberDocument;

  @Column(name = "date_document")
  private Date dateDocument;

  @Column(name = "due_date")
  private Date dueDate;

  @Column(name = "pay_date")
  private Date payDate;

  @Column(name = "description")
  private String description;

  @Column(name = "amount_paid")
  private BigDecimal amountPaid;

  @Column(name = "diference")
  private BigDecimal diference;

  @Column(name = "status")
  private String status;

  @Column(name = "value_of_Document")
  private BigDecimal valueOfDocument;

  @Column(name = "value_account_receivable")
  private BigDecimal valueAccountReceivable;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Client client;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Category category;

  @ManyToOne
  @JoinColumn(nullable = false)
  private CashFlow cashFlow;
}

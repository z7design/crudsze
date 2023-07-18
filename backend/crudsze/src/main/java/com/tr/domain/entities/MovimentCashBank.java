package com.tr.domain.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "movement_cash_bank")
public class MovimentCashBank {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "moviment_cash_bank_id")
  private Long movementCashBankId;

  private Integer numberDocument;
  private Date dateDocument;
  private Date dueDate;
  private String description;
  private BigDecimal amountPaid;
  private BigDecimal diference;
  private String status;
  private String tax;
  private BigDecimal valueOfDocument;
  private BigDecimal valueAccountPay;
  
}

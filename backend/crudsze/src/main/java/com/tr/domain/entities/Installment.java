package com.tr.domain.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "installment")
public class Installment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "installment_id")
  private Long installmentId;

  @Column(name = "date_paiment")
  private Date DatePayment;

  @Column(name = "value_pay")
  private BigDecimal valuePay;

  @Column(name = "discont")
  private BigDecimal discont;

  @Column(name = "interest")
  private BigDecimal interest;

  @Column(name = "additions")
  private BigDecimal additions;

  @Column(name = "fines")
  private BigDecimal fines;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Launch launch;
}

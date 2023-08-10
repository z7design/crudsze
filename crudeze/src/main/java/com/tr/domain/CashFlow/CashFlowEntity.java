package com.tr.domain.CashFlow;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cash_flow")
public class CashFlowEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "cash_flow_id")
  private Long cashFlowId;

  @Column(name = "dute_date")
  private Date duteDate;

  @Column(name = "payment_date")
  private Date paymentDate;

  @Column(name = "month_reference")
  private Date monthReference;

  @Column(name = "description")
  private String description;

  @Column(name = "payment_amount")
  private BigDecimal paymentAmount;

  @Column(name = "intallment_number")
  private String intallmentNumber;

  @Column(name = "total_payable")
  private BigDecimal totalPayable;

  @Column(name = "total_receivable")
  private BigDecimal totalReceivable;

  @Column(name = "balance")
  private BigDecimal balance;
}

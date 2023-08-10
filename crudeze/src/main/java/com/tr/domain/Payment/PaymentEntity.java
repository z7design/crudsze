package com.tr.domain.Payment;

import com.tr.domain.AccountPayble.AccountPaybleEntity;
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
@Table(name = "payment")
public class PaymentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "payment_id")
  private Long paymentId;

  @Column(name = "dute_date")
  private Date duteDate;

  @Column(name = "payment_date")
  private Date paymentDate;

  @Column(name = "month_reference")
  private String monthReference;

  @Column(name = "description")
  private String description;

  @Column(name = "payment_amount")
  private BigDecimal paymentAmount;

  @Column(name = "installment_number")
  private Integer installmentNumber;

  @ManyToOne
  @JoinColumn(nullable = false)
  private AccountPaybleEntity accountPayble;

}

package com.tr.domain.AccountPayble;

import com.tr.domain.Installment.InstallmentEntity;
import com.tr.domain.Supplier.SupplierEntity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account_payble")
@Entity
public class AccountPaybleEntity {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EmbeddedId
  @Column(name = "account_payble_id")
  private Long AccountPaybleId;

  @Column(name = "number_document")
  private Integer numberDocument;

  @Column(name = "date_document")
  private Date dateDocument;

  @Column(name = "due_date")
  private Date dueDate;

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

  @Column(name = "value_account_pay")
  private BigDecimal valueAccountPay;

  @ManyToOne
  @JoinColumn(nullable = false)
  private SupplierEntity supplier;

  @ManyToOne
  @JoinColumn(nullable = false)
  private InstallmentEntity installment;
}

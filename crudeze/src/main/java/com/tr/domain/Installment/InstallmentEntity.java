package com.tr.domain.Installment;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "installment")
public class InstallmentEntity {
  @Id
  @EmbeddedId
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "installment_id")
  private Long installmentId;

  @Column(name = "installment_number")
  private Integer installmentNumber;

  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @Column(name = "date_paiment")
  private LocalDate datePayment;

  @Column(name = "value_pay")
  private BigDecimal valuePay;

  @Column(name = "discont")
  private Double discont;

  @Column(name = "addtions")
  private Double addtions;

  @Column(name = "fines")
  private Double fines;
}

package com.tr.domain.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "expenses")
public class Expenses {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "expenses_id")
  private Long expensesId;

  @Column(name = "date")
  private Date date;

  @Column(name = "due_date")
  private Date dueDate;

  @Column(name = "description")
  private String description;

  @Column(name = "value_expenses")
  private BigDecimal valueExpenses;

  @Column(name = "date_pay")
  private Date payDay;

  @Column(name = "amount_paid")
  private BigDecimal amountPaid;

  @Column(name = "invoice")
  private String invoice;

  @Column(name = "locality")
  private String locality;
}

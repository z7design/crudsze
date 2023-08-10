package com.tr.domain.Expenses;

import com.tr.domain.Category.CategoryEntity;
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
@Table(name = "expenses")
public class ExpensesEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
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

  @ManyToOne
  @JoinColumn(nullable = false)
  private CategoryEntity category;
}

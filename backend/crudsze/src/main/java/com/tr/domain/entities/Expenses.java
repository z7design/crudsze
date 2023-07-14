package com.tr.domain.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
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

  @ManyToOne
  @JoinColumn(nullable = false)
  private Category category;

  public Expenses() {}

  public Expenses(
      Long expensesId,
      Date date,
      Date dueDate,
      String description,
      BigDecimal valueExpenses,
      Date payDay,
      BigDecimal amountPaid,
      String invoice,
      String locality,
      Category category) {
    this.expensesId = expensesId;
    this.date = date;
    this.dueDate = dueDate;
    this.description = description;
    this.valueExpenses = valueExpenses;
    this.payDay = payDay;
    this.amountPaid = amountPaid;
    this.invoice = invoice;
    this.locality = locality;
    this.category = category;
  }

  public Expenses(
      Date date,
      Date dueDate,
      String description,
      BigDecimal valueExpenses,
      Date payDay,
      BigDecimal amountPaid,
      String invoice,
      String locality,
      Category category) {
    this.date = date;
    this.dueDate = dueDate;
    this.description = description;
    this.valueExpenses = valueExpenses;
    this.payDay = payDay;
    this.amountPaid = amountPaid;
    this.invoice = invoice;
    this.locality = locality;
    this.category = category;
  }

  public Long getExpensesId() {
    return expensesId;
  }

  public void setExpensesId(Long expensesId) {
    this.expensesId = expensesId;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getValueExpenses() {
    return valueExpenses;
  }

  public void setValueExpenses(BigDecimal valueExpenses) {
    this.valueExpenses = valueExpenses;
  }

  public Date getPayDay() {
    return payDay;
  }

  public void setPayDay(Date payDay) {
    this.payDay = payDay;
  }

  public BigDecimal getAmountPaid() {
    return amountPaid;
  }

  public void setAmountPaid(BigDecimal amountPaid) {
    this.amountPaid = amountPaid;
  }

  public String getInvoice() {
    return invoice;
  }

  public void setInvoice(String invoice) {
    this.invoice = invoice;
  }

  public String getLocality() {
    return locality;
  }

  public void setLocality(String locality) {
    this.locality = locality;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Expenses)) return false;
    Expenses expenses = (Expenses) o;
    return getExpensesId().equals(expenses.getExpensesId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getExpensesId());
  }
}

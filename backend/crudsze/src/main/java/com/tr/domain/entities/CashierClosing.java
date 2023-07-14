package com.tr.domain.entities;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cashier_closing")
public class CashierClosing {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cashier_closing_id")
  private Long cashierClosingId;
}

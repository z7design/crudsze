package com.tr.domain.CashierClosing;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cashier_closing")
public class CashierClosingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cashier_closing_id")
  private Long cashierClosingId;
}

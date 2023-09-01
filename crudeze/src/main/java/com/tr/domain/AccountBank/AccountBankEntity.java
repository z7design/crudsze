package com.tr.domain.AccountBank;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account_bank")
public class AccountBankEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "account_bank_id")
  private Long accountBankId;

  @Column(name = "agency")
  private String agency;

  @Column(name = "agency_dv")
  private String agencyDV;

  @Column(name = "account")
  private String account;

  @Column(name = "account_dv")
  private String accountDV;

}

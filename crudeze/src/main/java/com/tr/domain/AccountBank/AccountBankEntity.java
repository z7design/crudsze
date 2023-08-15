package com.tr.domain.AccountBank;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account_bank")
public class AccountBankEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @EmbeddedId
  @GeneratedValue(strategy = GenerationType.AUTO)
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

package com.tr.domain.Banck;

import com.tr.domain.AccountBank.AccountBankEntity;
import com.tr.domain.Address.AddressEntity;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bank")
@Entity
public class BankEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "bank_id")
  private Long banckId;

  @Column(name = "name")
  private String name;

  @Column(name = "agency")
  private String agency;

  @Column(name = "code")
  private Integer code;

  @Column(name = "cheking_account")
  private String chekingAccount;

  @ManyToOne
  @JoinColumn(nullable = false)
  private AddressEntity address;

  @ManyToOne
  @JoinColumn(nullable = true)
  private AccountBankEntity accountBank;
}

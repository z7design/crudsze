package com.tr.domain.entities;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ledger")
public class LedgerAccount {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ledgerAccount_id")
  private Long ledgerAccountId;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "code")
  private Integer code;



 

}

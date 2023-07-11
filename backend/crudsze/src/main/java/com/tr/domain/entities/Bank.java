package com.tr.domain.entities;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "bank")
public class Bank {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "bank_id")
  private Long banckId;

  @Column(name = "name")
  private String name;

  @Column(name = "agency")
  private String agency;

  @Column(name = "codeAgencty")
  private Integer codeAgencty;

  @Column(name = "cheking_account")
  private String chekingAccount;

  @Column(name = "cnpj")
  private String cnpj;

  @ManyToOne
  @JoinColumn(nullable = false)
  private TypeAccount typeAccount;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Address address;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Phone phone;
}

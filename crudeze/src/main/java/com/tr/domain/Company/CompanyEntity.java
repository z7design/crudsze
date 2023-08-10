package com.tr.domain.Company;

import com.tr.domain.Address.AddressEntity;
import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
@Entity
public class CompanyEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "company_id")
  private Long companyId;

  @Column(name = "name")
  private String name;

  @Column(name = "email")
  private String email;

  @Column(name = "phone")
  private String phone;

  @Column(name = "celphone")
  private String celphone;

  @Column(name = "date_registration")
  private Date dateRegistration;

  @Column(name = "logo")
  private String logo;

  @Column(name = "cnpj")
  private String cnpj;

  @Column(name = "state_register")
  private String stateRegister;

  @Column(name = "municiapl_register")
  private String municipalRegister;

  @ManyToOne
  @JoinColumn(nullable = false)
  private AddressEntity address;

}

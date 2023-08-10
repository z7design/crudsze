package com.tr.domain.Employees;

import com.tr.domain.Address.AddressEntity;
import com.tr.domain.Departament.DepartamentEntity;
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
@Table(name = "employees")
public class EmployeesEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "employess_id")
  private Long employeesId;

  @Column(name = "name")
  private String name;

  @Column(name = "email")
  private String email;

  @Column(name = "cpf")
  private String cpf;

  @Column(name = "genereal_register")
  private String generealRegister;

  @Column(name = "salary")
  private BigDecimal salary;

  @Column(name = "function")
  private String function;

  @Column(name = "number_licence_driver")
  private String numberLicenceDriver;

  @Column(name = "spouse")
  private String spouse;

  @Column(name = "number_children")
  private Integer numberChildren;

  @Column(name = "schooling")
  private String schooling;

  @Column(name = "work_hours")
  private String workHours;

  @Column(name = "foto")
  private String foto;

  @Column(name = "birthday")
  private Date birthday;

  @Column(name = "work_card")
  private String workCard;

  @Column(name = "pispasep")
  private String pispasep;

  @Column(name = "admission")
  private Date admission;

  @Column(name = "demission")
  private Date demission;

  @Column(name = "exames")
  private String exames;

  @ManyToOne
  @JoinColumn(nullable = false)
  private AddressEntity address;

  @ManyToOne
  @JoinColumn(nullable = false)
  private DepartamentEntity departament;

}

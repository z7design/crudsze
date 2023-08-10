package com.tr.domain.Driver;

import com.tr.domain.Address.AddressEntity;
import com.tr.domain.Departament.DepartamentEntity;
import com.tr.domain.Document.DocumentEntity;
import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "driver")
public class DriverEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "driver_id")
  private Long driverId;

  @Column(name = "name")
  private String name;

  @Column(name = "dateBirthday")
  private Date dateBirthday;

  @Column(name = "general_register")
  private String generalRegister;

  @Column(name = "cpf")
  private String cpf;

  @Column(name = "phone")
  private String phone;

  @Column(name = "celphone")
  private String celphone;

  @Column(name = "email")
  private String email;

  @ManyToOne
  @JoinColumn(nullable = false)
  private AddressEntity address;

  @ManyToOne
  @JoinColumn(nullable = false)
  private DocumentEntity document;

  @ManyToOne
  @JoinColumn(nullable = false)
  private DepartamentEntity departament;
}

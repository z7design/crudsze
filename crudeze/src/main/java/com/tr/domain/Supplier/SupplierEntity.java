package com.tr.domain.Supplier;

import com.tr.domain.Address.AddressEntity;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "supplier")
public class SupplierEntity {
  @Id
  @EmbeddedId
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "supllier_id")
  private Long supplierId;

  @Column(name = "name")
  private String name;

  @Column(name = "corparete_name")
  private String corpareteName;

  @Column(name = "fantasy_name")
  private String fantasyName;

  @Column(name = "cnpj")
  private String cnpj;

  @Column(name = "responsable")
  private String responsable;

  @Column(name = "email")
  private String email;

  @Column(name = "municipal_registration")
  private String municipalRegistration;

  @Column(name = "phone")
  private String phone;

  @Column(name = "celphone")
  private String celphone;

  @Column(name = "date_of_lastPurchase")
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private LocalDate dateOfLastPurchase;

  @Column(name = "date_registration")
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private LocalDate dateRegistration;

  @ManyToOne
  @JoinColumn(nullable = false)
  private AddressEntity address;
}

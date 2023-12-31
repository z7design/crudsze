package com.tr.domain.Client;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.tr.domain.Address.AddressEntity;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class ClientEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "client_id")
  private Long clientId;

  @Column(name = "name")
  private String name;

  @Column(name = "corporate_name")
  private String corporateName;

  @Column(name = "fantasy_name")
  private String fantasyName;

  @Column(name = "email")
  private String email;

  @Column(name = "cpf")
  private String cpf;

  @Column(name = "cnpj")
  private String cnpj;

  @Column(name = "general_register")
  private String generalRegister;

  @Column(name = "entiy_registration")
  private String entiyRegistration;

  @Column(name = "date_of_birth")
  @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate dateOfBirth;

  @Column(name = "state_registration")
  private String stateRegistration;

  @Column(name = "municipal_registration")
  private String municipalRegistration;

  @ManyToOne
  @JoinColumn(nullable = false)
  private AddressEntity address;

  @Column(name = "observation")
  private String observation;

  @Column(name = "registrarion_date")
  private Date registrarionDate;
}

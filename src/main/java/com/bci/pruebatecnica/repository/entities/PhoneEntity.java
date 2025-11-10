package com.bci.pruebatecnica.repository.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "phones")
public class PhoneEntity {
  @Id
  @GeneratedValue
  @UuidGenerator
  private String id;

  @Column(name = "number")
  private String number;

  @Column(name = "cityCode")
  private String cityCode;

  @Column(name = "countryCode")
  private String countryCode;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity user;

}

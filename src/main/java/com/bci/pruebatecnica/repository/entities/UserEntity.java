package com.bci.pruebatecnica.repository.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
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
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue
  @UuidGenerator
  private String id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PhoneEntity> phoneEntities;

  @Column(name = "created_date", nullable = false)
  private LocalDateTime created;

  @Column(name = "modified_date")
  private LocalDateTime modified;

  @Column(name = "last_login_date")
  private LocalDateTime lastLogin;

  @Column(name = "access_token")
  private String token;

  @Column(name = "is_active")
  private Boolean isActive;
}

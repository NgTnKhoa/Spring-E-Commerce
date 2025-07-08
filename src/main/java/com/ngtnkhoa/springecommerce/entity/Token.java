package com.ngtnkhoa.springecommerce.entity;

import com.ngtnkhoa.springecommerce.enums.TokenType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tokens")
public class Token extends Base {

  @Column(name = "token")
  private String token;

  @Column(name = "token_type")
  @Enumerated(EnumType.STRING)
  private TokenType tokenType;

  @Column(name = "expired")
  private boolean expired;

  @Column(name = "revoked")
  private boolean revoked;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}

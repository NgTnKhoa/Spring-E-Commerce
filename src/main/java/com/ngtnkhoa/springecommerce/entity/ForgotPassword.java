package com.ngtnkhoa.springecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "forgot_passwords")
public class ForgotPassword extends Base {

  private Integer otp;

  private Date expirationDate;

  @OneToOne
  private User user;
}

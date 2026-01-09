package com.ngtnkhoa.springecommerce.entity;

import com.ngtnkhoa.springecommerce.enums.PaymentMethod;
import com.ngtnkhoa.springecommerce.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payments")
public class Payment extends Base {

  @Column(name = "transaction_code")
  private String transactionCode;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private PaymentStatus status;

  @Enumerated(EnumType.STRING)
  @Column(name = "method")
  private PaymentMethod method;

  @Column(name = "amount")
  private String amount;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;
}

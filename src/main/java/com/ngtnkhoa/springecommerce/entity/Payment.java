package com.ngtnkhoa.springecommerce.entity;

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

  @Column(name = "status")
  private String status;

  @Column(name = "method")
  private String method;

  @Column(name = "amount")
  private String amount;

  @Column(name = "transaction_id")
  private String transactionId;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;
}

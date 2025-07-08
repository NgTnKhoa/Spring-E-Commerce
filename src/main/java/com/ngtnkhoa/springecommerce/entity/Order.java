package com.ngtnkhoa.springecommerce.entity;

import jakarta.persistence.*;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends Base {

  @Column(name = "amount")
  private double amount;

  @Column(name = "status")
  private String status;

  @Column(name = "address")
  private String address;

  @OneToMany(mappedBy = "order")
  private List<OrderItem> orderItems;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToOne(mappedBy = "order")
  private Payment payment;
}

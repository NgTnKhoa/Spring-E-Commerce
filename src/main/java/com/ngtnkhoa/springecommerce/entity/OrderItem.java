package com.ngtnkhoa.springecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem extends Base {

  @Column(name = "product_id", nullable = false)
  private String productId;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "price")
  private double price;

  @Column(name = "amount")
  private double amount;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;
}

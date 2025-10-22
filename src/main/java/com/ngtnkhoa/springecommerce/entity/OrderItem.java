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

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "unit_price")
  private double unitPrice;

  @Column(name = "discount")
  private double discount;

  @Column(name = "total_price")
  private double totalPrice;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @ManyToOne
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;
}

package com.ngtnkhoa.springecommerce.entity;

import com.ngtnkhoa.springecommerce.enums.OrderStatus;
import jakarta.persistence.*;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends Base {

  @Column(name = "order_code")
  private String orderCode;

  @Column(name = "total_amount")
  private double totalAmount;

  @Column(name = "address")
  private String address;

  @Column(name = "status")
  private OrderStatus status;

  @Column(name = "payment_method")
  private String paymentMethod;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "order")
  @Cascade(CascadeType.ALL)
  private List<Payment> payments;

  @OneToMany(mappedBy = "order")
  @Cascade(CascadeType.ALL)
  private List<OrderItem> orderItems;
}

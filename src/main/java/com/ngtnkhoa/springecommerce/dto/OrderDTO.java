package com.ngtnkhoa.springecommerce.dto;

import com.ngtnkhoa.springecommerce.enums.OrderStatus;
import com.ngtnkhoa.springecommerce.enums.PaymentMethod;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
  private Long id;
  private String orderCode;
  private double totalAmount;
  private double discountAmount;
  private double finalAmount;
  private OrderStatus status;
  private PaymentMethod paymentMethod;
  private UserDTO user;
  private List<PaymentDTO> payments;
  private List<OrderItemDTO> orderItems;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}

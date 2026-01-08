package com.ngtnkhoa.springecommerce.dto;

import com.ngtnkhoa.springecommerce.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
  private Long id;
  private String orderCode;
  private double totalAmount;
  private String address;
  private OrderStatus status;
  private String paymentMethod;
  private UserDTO user;
  private List<PaymentDTO> payments;
  private List<OrderItemDTO> orderItems;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}

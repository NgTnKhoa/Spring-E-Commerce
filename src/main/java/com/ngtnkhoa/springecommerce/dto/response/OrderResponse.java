package com.ngtnkhoa.springecommerce.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class OrderResponse {
  private Long id;
  private double amount;
  private String status;
  private String address;
  private UserResponse user;
  private PaymentResponse payment;
  private List<OrderItemResponse> orderItems;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}

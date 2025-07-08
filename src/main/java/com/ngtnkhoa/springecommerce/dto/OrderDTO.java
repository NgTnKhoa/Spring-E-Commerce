package com.ngtnkhoa.springecommerce.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
  private Long id;
  private double amount;
  private String status;
  private String address;
  private UserDTO user;
  private PaymentDTO payment;
  private List<OrderItemDTO> orderItems;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}

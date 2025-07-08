package com.ngtnkhoa.springecommerce.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderItemDTO {
  private Long id;
  private Long productId;
  private Long orderId;
  private int quantity;
  private double price;
  private double amount;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}

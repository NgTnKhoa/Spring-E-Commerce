package com.ngtnkhoa.springecommerce.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderItemResponse {
  private Long id;
  private Long productId;
  private Long orderId;
  private int quantity;
  private double unitPrice;
  private double discount;
  private double totalPrice;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}

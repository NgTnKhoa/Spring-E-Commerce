package com.ngtnkhoa.springecommerce.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderItemRequest {

  @NotNull(message = "Product ID is required")
  private Long productId;

  @NotNull(message = "Order ID is required")
  private Long orderId;

  @Positive(message = "Amount must be greater than 0")
  private int quantity;

  @Positive(message = "Total amount must be greater than 0")
  private double totalAmount;

  private double discountAmount;

  @Positive(message = "Final amount must be greater than 0")
  private double finalAmount;
}

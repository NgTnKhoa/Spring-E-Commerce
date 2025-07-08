package com.ngtnkhoa.springecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderItemRequest {

  @NotBlank(message = "Product ID is required")
  private Long productId;

  @NotBlank(message = "Order ID is required")
  private Long orderId;

  @Positive(message = "Amount must be greater than 0")
  private int quantity;

  @Positive(message = "Amount must be greater than 0")
  private double price;

  @Positive(message = "Amount must be greater than 0")
  private double amount;
}

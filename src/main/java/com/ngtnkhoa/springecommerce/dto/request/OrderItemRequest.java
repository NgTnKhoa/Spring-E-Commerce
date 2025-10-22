package com.ngtnkhoa.springecommerce.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

  @Positive(message = "Amount must be greater than 0")
  private double unitPrice;

  @Min(value = 0, message = "Discount must be at least 0")
  @Max(value = 100, message = "Discount must be not exceed 100")
  private double discount;

  @Positive(message = "Amount must be greater than 0")
  private double totalPrice;
}

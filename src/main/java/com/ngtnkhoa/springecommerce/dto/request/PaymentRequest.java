package com.ngtnkhoa.springecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PaymentRequest {

  @NotNull(message = "Order ID is required")
  private Long orderId;

  @NotBlank(message = "Transaction ID is required")
  private String transactionId;

  @NotBlank(message = "Status is required")
  private String status;

  @NotBlank(message = "Method is required")
  private String method;

  @Positive(message = "Amount must be greater than 0")
  private double amount;
}

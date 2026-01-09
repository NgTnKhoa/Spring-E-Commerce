package com.ngtnkhoa.springecommerce.dto.request;

import com.ngtnkhoa.springecommerce.enums.PaymentMethod;
import com.ngtnkhoa.springecommerce.enums.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PaymentRequest {

  @NotNull(message = "Order ID is required")
  private Long orderId;

  @NotBlank(message = "Transaction code is required")
  private String transactionCode;

  @NotNull(message = "Status is required")
  private PaymentStatus status;

  @NotNull(message = "Method is required")
  private PaymentMethod method;

  @Positive(message = "Amount must be greater than 0")
  private double amount;
}

package com.ngtnkhoa.springecommerce.dto.request;

import java.util.List;

import com.ngtnkhoa.springecommerce.enums.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderRequest {

  @NotNull(message = "User ID is required")
  private Long userId;

  @Positive(message = "Total amount must be greater than 0")
  private double totalAmount;

  @NotBlank(message = "Status is required")
  private OrderStatus status;

  @NotBlank(message = "Address is required")
  private String address;

  @NotBlank(message = "Payment method is required")
  private String paymentMethod;

  @NotEmpty(message = "Order must contain at least one item")
  private List<@Valid OrderItemRequest> orderItems;
}

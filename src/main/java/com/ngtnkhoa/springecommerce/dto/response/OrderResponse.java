package com.ngtnkhoa.springecommerce.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.ngtnkhoa.springecommerce.enums.OrderStatus;
import com.ngtnkhoa.springecommerce.enums.PaymentMethod;
import lombok.Data;

@Data
public class OrderResponse {
  private Long id;
  private String orderCode;
  private double totalAmount;
  private double discountAmount;
  private double finalAmount;
  private OrderStatus status;
  private PaymentMethod paymentMethod;
  private UserResponse user;
  private List<OrderItemResponse> orderItems;
  private List<PaymentResponse> payments;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}

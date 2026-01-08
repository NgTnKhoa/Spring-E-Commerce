package com.ngtnkhoa.springecommerce.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.ngtnkhoa.springecommerce.dto.OrderItemDTO;
import com.ngtnkhoa.springecommerce.dto.PaymentDTO;
import com.ngtnkhoa.springecommerce.dto.UserDTO;
import com.ngtnkhoa.springecommerce.enums.OrderStatus;
import lombok.Data;

@Data
public class OrderResponse {
  private Long id;
  private String orderCode;
  private double totalAmount;
  private OrderStatus status;
  private String address;
  private String paymentMethod;
  private UserResponse user;
  private List<OrderItemResponse> orderItems;
  private List<PaymentResponse> payments;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}

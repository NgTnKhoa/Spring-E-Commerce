package com.ngtnkhoa.springecommerce.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.ngtnkhoa.springecommerce.dto.OrderItemDTO;
import com.ngtnkhoa.springecommerce.dto.PaymentDTO;
import com.ngtnkhoa.springecommerce.dto.UserDTO;
import lombok.Data;

@Data
public class OrderResponse {
  private Long id;
  private String orderCode;
  private double totalAmount;
  private String status;
  private String address;
  private String paymentMethod;
  private UserResponse user;
  private List<PaymentResponse> payments;
  private List<OrderItemResponse> orderItems;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}

package com.ngtnkhoa.springecommerce.dto.response;

import com.ngtnkhoa.springecommerce.enums.PaymentMethod;
import com.ngtnkhoa.springecommerce.enums.PaymentStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentResponse {
  private Long id;
  private Long orderId;
  private String transactionCode;
  private PaymentStatus status;
  private PaymentMethod method;
  private Double amount;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}

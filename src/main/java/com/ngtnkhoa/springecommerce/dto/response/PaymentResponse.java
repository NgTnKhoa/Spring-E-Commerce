package com.ngtnkhoa.springecommerce.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentResponse {
  private Long id;
  private Long orderId;
  private String transactionCode;
  private String status;
  private String method;
  private Double amount;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}

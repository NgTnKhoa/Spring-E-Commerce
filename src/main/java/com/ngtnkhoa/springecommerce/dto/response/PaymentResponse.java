package com.ngtnkhoa.springecommerce.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentResponse {
  private Long id;
  private String transactionId;
  private String status;
  private String method;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}

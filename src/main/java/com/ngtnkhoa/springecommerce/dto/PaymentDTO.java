package com.ngtnkhoa.springecommerce.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDTO {
  private Long id;
  private String transactionId;
  private String status;
  private String method;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}

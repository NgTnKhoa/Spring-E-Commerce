package com.ngtnkhoa.springecommerce.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentDTO {
  private Long id;
  private Long orderId;
  private String transactionCode;
  private String status;
  private String method;
  private double amount;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}

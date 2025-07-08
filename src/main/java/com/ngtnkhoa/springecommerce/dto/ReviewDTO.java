package com.ngtnkhoa.springecommerce.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewDTO {
  private Long id;
  private Long userId;
  private Long productId;
  private int rating;
  private String content;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}

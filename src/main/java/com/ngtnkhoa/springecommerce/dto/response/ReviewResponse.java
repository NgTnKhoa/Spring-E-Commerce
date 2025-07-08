package com.ngtnkhoa.springecommerce.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReviewResponse {
  private Long id;
  private Long userId;
  private Long productId;
  private int rating;
  private String content;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}

package com.ngtnkhoa.springecommerce.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryDTO {
  private Long id;
  private String name;
  private String slug;
  private String description;
  private String image;
  private boolean featured;
  private String status;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}

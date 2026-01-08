package com.ngtnkhoa.springecommerce.dto;

import com.ngtnkhoa.springecommerce.enums.CategoryStatus;
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
  private CategoryStatus status;
  private Long parentId;
  private String parentName;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}

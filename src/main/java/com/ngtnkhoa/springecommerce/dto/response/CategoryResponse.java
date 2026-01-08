package com.ngtnkhoa.springecommerce.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.ngtnkhoa.springecommerce.enums.CategoryStatus;
import lombok.Data;

@Data
public class CategoryResponse {
  private Long id;
  private String name;
  private String slug;
  private String description;
  private String image;
  private boolean featured;
  private CategoryStatus status;
  private Long parentId;
  private List<CategoryResponse> children;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}

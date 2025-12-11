package com.ngtnkhoa.springecommerce.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class CategoryResponse {
  private Long id;
  private String name;
  private String slug;
  private String description;
  private String image;
  private boolean featured;
  private String status;
  private Long parentId;
  private List<CategoryResponse> children;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}

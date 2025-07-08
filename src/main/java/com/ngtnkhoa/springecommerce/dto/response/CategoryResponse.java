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
  private List<String> images;
  private boolean featured;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}

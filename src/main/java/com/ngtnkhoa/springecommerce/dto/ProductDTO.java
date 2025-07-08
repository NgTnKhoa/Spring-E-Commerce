package com.ngtnkhoa.springecommerce.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductDTO {
  private Long id;
  private Long categoryId;
  private String name;
  private String slug;
  private String description;
  private List<String> images;
  private double price;
  private double discount;
  private String status;
  private boolean featured;
  private List<String> colors;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}

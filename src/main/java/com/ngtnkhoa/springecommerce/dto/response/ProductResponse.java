package com.ngtnkhoa.springecommerce.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductResponse {
  private Long id;
  private String name;
  private String slug;
  private String description;
  private List<String> images;
  private double price;
  private double discount;
  private String brand;
  private String status;
  private boolean featured;
  private List<String> colors;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}

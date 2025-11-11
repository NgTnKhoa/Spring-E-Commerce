package com.ngtnkhoa.springecommerce.dto;

import com.ngtnkhoa.springecommerce.entity.emb.ProductColor;
import com.ngtnkhoa.springecommerce.entity.emb.ProductImage;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductDTO {
  private Long id;
  private String name;
  private String slug;
  private String description;
  private double price;
  private double discount;
  private String brand;
  private String status;
  private boolean featured;
  private String mainImage;
  private List<ProductImage> images;
  private List<ProductColor> colors;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}

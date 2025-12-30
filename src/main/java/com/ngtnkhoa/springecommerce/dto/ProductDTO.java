package com.ngtnkhoa.springecommerce.dto;

import com.ngtnkhoa.springecommerce.entity.emb.ProductColor;
import com.ngtnkhoa.springecommerce.entity.emb.ProductImage;
import com.ngtnkhoa.springecommerce.enums.ProductStatus;
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
  private int stock;
  private int rating;
  private double salePrice;
  private String brand;
  private ProductStatus status;
  private boolean featured;
  private String mainImage;
  private CategoryDTO category;
  private List<ProductImage> images;
  private List<ProductColor> colors;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
}

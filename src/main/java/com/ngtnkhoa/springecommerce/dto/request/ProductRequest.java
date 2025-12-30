package com.ngtnkhoa.springecommerce.dto.request;

import com.ngtnkhoa.springecommerce.entity.emb.ProductColor;
import com.ngtnkhoa.springecommerce.entity.emb.ProductImage;
import com.ngtnkhoa.springecommerce.enums.ProductBadge;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class ProductRequest {

  @NotNull(message = "Category is required")
  private Long categoryId;

  @NotBlank(message = "Name is required")
  private String name;

  @NotBlank(message = "Description is required")
  private String description;

  @NotNull(message = "Price is required")
  @Positive(message = "Amount must be greater than 0")
  private double price;

  @NotNull(message = "Stock is required")
  private int stock;

  @Min(value = 0, message = "Rating must be at least 0")
  @Max(value = 5, message = "Rating must be not exceed 5")
  private int rating = 0;

  private double salePrice;

  @NotBlank(message = "Brand is required")
  private String brand;

  @NotBlank(message = "Status is required")
  private String status;

  private boolean featured;

  @NotBlank(message = "Main image is required")
  private String mainImage;

  @NotNull(message = "Badge is required")
  private ProductBadge badge;

  @NotEmpty(message = "At least one image is required")
  @Size(max = 5, message = "At most 5 images allowed")
  private List<@Valid ProductImage> images;

  @NotEmpty(message = "At least one color is required")
  @Size(max = 5, message = "At most 5 colors allowed")
  private List<@Valid ProductColor> colors;
}

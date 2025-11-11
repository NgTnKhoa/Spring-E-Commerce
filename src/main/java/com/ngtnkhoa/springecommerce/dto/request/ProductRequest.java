package com.ngtnkhoa.springecommerce.dto.request;

import com.ngtnkhoa.springecommerce.entity.emb.ProductColor;
import com.ngtnkhoa.springecommerce.entity.emb.ProductImage;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductRequest {

  @NotNull(message = "Category ID is required")
  private Long categoryId;

  @NotBlank(message = "Name is required")
  private String name;

  @NotBlank(message = "Description is required")
  private String description;

  @Positive(message = "Amount must be greater than 0")
  private double price;

  private double discount;

  @NotBlank(message = "Brand is required")
  private String brand;

  @NotBlank(message = "Status is required")
  private String status;

  private boolean featured;

  @NotBlank(message = "Main image is required")
  private String mainImage;

  @NotEmpty(message = "At least one image is required")
  @Size(max = 5, message = "At most 5 images allowed")
  private List<@Valid ProductImage> images;

  @NotEmpty(message = "At least one color is required")
  @Size(max = 5, message = "At most 5 colors allowed")
  private List<@Valid ProductColor> colors;
}

package com.ngtnkhoa.springecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CategoryRequest {

  @NotBlank(message = "Name is required")
  @Size(max = 100, message = "Name must not exceed 100 characters")
  private String name;

  @NotBlank(message = "Description is required")
  @Size(max = 1000, message = "Description must not exceed 1000 characters")
  private String description;

  @NotBlank(message = "Image is required")
  private String image;

  private boolean featured;

  @NotBlank(message = "Status is required")
  private String status;
}

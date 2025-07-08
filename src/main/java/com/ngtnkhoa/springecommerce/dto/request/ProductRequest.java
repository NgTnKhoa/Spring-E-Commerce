package com.ngtnkhoa.springecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class ProductRequest {

    @NotBlank(message = "Category ID is required")
    private Long categoryId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotEmpty(message = "At least one image is required")
    @Size(max = 5, message = "At most 5 images allowed")
    private List<@NotBlank(message = "Each image URL must not be blank") String> images;

    @Positive(message = "Amount must be greater than 0")
    private double price;

    private double discount;

    @NotBlank(message = "Status is required")
    private String status;

    @NotEmpty(message = "At least one colors is required")
    private List<@NotBlank(message = "Each color must not be blank") String> colors;

    private boolean featured;
}

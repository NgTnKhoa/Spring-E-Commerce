package com.ngtnkhoa.springecommerce.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ReviewRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Product ID is required")
    private Long productId;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must not exceed 5")
    private int rating;

    @NotBlank(message = "Content is required")
    @Size(max = 1000, message = "Content must not exceed 1000 characters")
    private String content;
}
